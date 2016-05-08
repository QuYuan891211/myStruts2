package com.qy.dao;

import com.qy.entity.UserInfo;

public class UserDao {
	//1.模拟登陆
	public UserInfo login(UserInfo userInfo){
		if ("tom".equals(userInfo.getUserName()) && "888".equals(userInfo.getPwd())){
			return userInfo;
		}
		return null;
	}
	//2.模拟注册
	public void register(UserInfo userInfo){
		System.out.println("注册成功" + "用户" + userInfo.getUserName());
	}
	
}
