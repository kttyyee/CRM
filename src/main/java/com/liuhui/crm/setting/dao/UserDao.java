package com.liuhui.crm.setting.dao;

import com.liuhui.crm.setting.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}
