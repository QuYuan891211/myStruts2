package com.qy.service;

import com.qy.dao.UserDao;
import com.qy.entity.UserInfo;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public UserInfo login(UserInfo userInfo){
		return userDao.login(userInfo);
	}
	public void register(UserInfo userInfo){
		userDao.register(userInfo);
	}
	
}
