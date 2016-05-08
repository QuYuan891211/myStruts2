package com.qy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.entity.UserInfo;
import com.qy.framework.action.LoginAction;
import com.qy.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginAction loginAction = new LoginAction();
		Object url = loginAction.Login(request, response);
		if (url instanceof String){
			response.sendRedirect(request.getContextPath() + url.toString());
		}else{
			((RequestDispatcher)url).forward(request,response);
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
