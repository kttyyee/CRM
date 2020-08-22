package com.liuhui.crm.setting.service.impl;

import com.liuhui.crm.setting.dao.UserDao;
import com.liuhui.crm.setting.domain.User;
import com.liuhui.crm.setting.service.UserService;
import com.liuhui.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) {
        return null;
    }
}
