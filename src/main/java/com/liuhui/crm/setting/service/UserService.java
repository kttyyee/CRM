package com.liuhui.crm.setting.service;

import com.liuhui.crm.setting.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip);
}
