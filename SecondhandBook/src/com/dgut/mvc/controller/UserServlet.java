package com.dgut.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.BookService;
import com.dgut.mvc.service.UserService;

@WebServlet("*.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = null;
	private BookService bookService = null;

	public UserServlet() {
		super();
		userService = new UserService();
		bookService = new BookService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - 3);
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 进入个人空间
	public void userSpace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = userService.getUserBySession(request, response);
		if (user == null) {
			return;
		}
		List<Book> books = bookService.getUserAllBooks(user.getUid());
		request.setAttribute("books", books);
		request.setAttribute("username", user.getUserName());
		request.getRequestDispatcher("/userSpace.jsp").forward(request, response);

	}

	// 进入主页
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userService.getUserBySession(request, response);
		if (user == null) {
			return;
		}

		List<Book> books = bookService.getAllBooks();
		request.setAttribute("books", books);
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("isAdministrator", user.getIsAdministrator());
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	// 进入修改密码页面
	public void userInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = userService.getUserBySession(request, response);
		if (user == null) {
			return;
		}

		request.setAttribute("username", user.getUserName());
		request.getRequestDispatcher("WEB-INF/views/userInput.jsp").forward(request, response);

	}

	// 登出
	public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// 修改密码
	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String qrnewPassword = request.getParameter("qrnewPassword");
		if (!newPassword.equals(qrnewPassword)) {
			request.setAttribute("title", "修改密码错误");
			request.setAttribute("state", "两次密码不一致");
			request.setAttribute("nextWeb", "userSpace.do");
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
		} else {
			// 验证旧密码
			User user = userService.getUserBySession(request, response);

			if (user != null) {
				if (user.getPassword().equals(oldPassword)) {
					userService.changePasswd(user.getUid(), newPassword);
					request.setAttribute("title", "修改密码成功");
					request.setAttribute("state", "修改密码成功");
					request.setAttribute("nextWeb", "userSpace.do");
					request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
				}
				// 旧密码错误
				else {
					request.setAttribute("title", "修改密码错误");
					request.setAttribute("state", "旧密码错误");
					request.setAttribute("nextWeb", "userSpace.do");
					request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
				}
			}
		}
	}

	// 进入管理员页面
	public void administrator(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userService.getUserBySession(request, response);
		if (user == null)
			return;

		List<User> users = userService.fetchAllUser();
		// 删除管理员自己
		for (User temp : users) {
			if (user.getUid() == temp.getUid()) {
				users.remove(temp);
				break;
			}
		}
		request.setAttribute("administrator", user);
		request.setAttribute("users", users);
		request.getRequestDispatcher("WEB-INF/views/administrator.jsp").forward(request, response);
	}

	// 注销账号
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.deleteUser(id);
		// request.getRequestDispatcher("WEB-INF/views/administrator.jsp").forward(request,
		// response);
		administrator(request, response);
	}

	// 更改用户权限
	public void changeUserPostRight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userService.searchUser(id);
		userService.changeUserPostRight(user);
		administrator(request, response);
	}
	
	// 更改用户身份权限
		public void changeUserAdministratorRight(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			int id=Integer.parseInt(request.getParameter("id"));
			User user=userService.searchUser(id);
			userService.changeUserAdministratorRight(user);
			administrator(request, response);
		}
}
