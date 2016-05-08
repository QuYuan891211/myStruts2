package com.qy.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.entity.UserInfo;
import com.qy.service.UserService;

public class RegisterAction {
	public Object register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;
		//1.获取请求数据并封装
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(name);
		userInfo.setPwd(password);
		//2.调用service
		UserService userService = new UserService();
		userService.register(userInfo);
		//3.跳转 
		//url = request.getContextPath() + "/login.jsp";
			
		return "registerSuccess";
	}
}
