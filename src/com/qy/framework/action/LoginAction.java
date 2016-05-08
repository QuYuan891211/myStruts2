package com.qy.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.entity.UserInfo;
import com.qy.service.UserService;

public class LoginAction {
	public Object execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		return null;
	}
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object url = null;
		//1.获取请求数据并封装
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(name);
		userInfo.setPwd(password);
		//2.调用service
		UserService userService = new UserService();
		UserInfo userInfo2 = userService.login(userInfo);
		//3.跳转 
		if(userInfo2 == null){
			url = "loginFailed";
			
		}else{
			request.getSession().setAttribute("userInfo2", userInfo2);
			url = "LoginSuccess"; 
		}
		return url;

	}
}
