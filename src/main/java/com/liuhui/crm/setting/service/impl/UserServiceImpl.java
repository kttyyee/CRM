package com.liuhui.crm.setting.service.impl;

import com.liuhui.crm.exceptions.LoginException;
import com.liuhui.crm.setting.dao.UserDao;
import com.liuhui.crm.setting.domain.User;
import com.liuhui.crm.setting.service.UserService;
import com.liuhui.crm.utils.DateTimeUtil;
import com.liuhui.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);

        if (user==null){
            throw new LoginException("账号密码错误");
        }
        //如果程序能够成功执行到这里，说明账号密码正确，需要继续向下验证
        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号已失效");
        }
        //判断锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }

        //判断Ip地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginException("IP地址受限");
        }
        return user;
    }
}
