package com.dgut.mvc.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.UserService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");		//已经登录
		
		if (user != null) {
			new UserServlet().index(request, response);
		} else {
			//session过期或从未登录
			String user_name = request.getParameter("username");
			String user_password = request.getParameter("password");

			UserService service = new UserService();
			user = service.login(user_name, user_password);
			if (user != null) {
				request.getSession().setAttribute("user", user); // 用session保存用户信息
				new UserServlet().index(request, response);
			} else {
				request.setAttribute("title", "登录错误");
				request.setAttribute("state", "用户名或密码错误");
				request.setAttribute("nextWeb", "login.jsp");
				request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
