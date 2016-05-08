package com.qy.framework;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.bean.ActionMapping;
import com.qy.bean.ActionMappingManager;
import com.qy.bean.Result;

/*   此项目的核心控制器
   1.所有请求都提交这里*/
public class ActionServlet extends HttpServlet{
	  //servlet在第一次调用的时候执行，之后每次调用并不执行
	private ActionMappingManager actionMappingManager;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//希望在启动的时候调用
		System.out.println("1111111111111111ActionServlet.init()");
		 actionMappingManager = new ActionMappingManager();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//1.获取请求都uri,得到请求路径
			String uri = request.getRequestURI();
			//2.根据路径名称读取配置文件得到类都全名
			String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".action"));
			ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
			//3.得到类的方法名
			String className = actionMapping.getClassname();
			String methodName = actionMapping.getMethod();
			
			//4.进行反射，创建对象，调用方法获取调用的标志
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			String returnFlag = (String) m.invoke(obj,request,response);
			
			//5.拿到标记对应的页面,以及跳转类型
		
			Result r = actionMapping.getResult(returnFlag);
			String type =r.getType();
			String page = r.getPage();
			
			//6.进行跳转处理－－－1.重定向 2.转发
			
			if("redirect".equals(type)){
				response.sendRedirect(request.getContextPath() + page);
			}else{
				request.getRequestDispatcher(page).forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
}
