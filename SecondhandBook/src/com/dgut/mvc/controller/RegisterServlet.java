package com.dgut.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.UserService;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_name = request.getParameter("username");
		String user_password = request.getParameter("password");
		String user_qrpassword = request.getParameter("qrpassword");

		if (!user_password.equals(user_qrpassword)) {
			request.setAttribute("state", "两次密码不一致");
			request.setAttribute("nextWeb", "register.jsp");
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
			return;
		} else {
			UserService service = new UserService();
			User ub = service.findByUserName(user_name);
			if (ub == null) {
				User user = new User(user_name, user_password);
				service.addUser(user);
				request.setAttribute("state", "注册成功！");
				request.setAttribute("nextWeb", "login.jsp");
				request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
			} else {
				request.setAttribute("state", "该用户已存在！");
				request.setAttribute("nextWeb", "register.jsp");
				request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
