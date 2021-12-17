package com.dgut.mvc.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgut.mvc.bean.User;
import com.dgut.mvc.dao.UserDao;
import com.dgut.mvc.daoImpl.UserDaoJdbcImpl;
import com.jdbc.dgut.mvc.utils.jdbcTools;

public class UserService {
	private UserDao dao;

	public UserService() {
		this.dao = new UserDaoJdbcImpl();
	}

	public void addUser(User User) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			connection.setAutoCommit(false);
			dao.addUser(connection, User);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	public void deleteUser(int uid) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			connection.setAutoCommit(false);
			dao.deleteUser(connection, uid);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	public User searchUser(int uid) {
		Connection connection = null;
		User user=null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			user=dao.searchUser(connection, uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return user;
	}
	
	public User findByUserName(String userName) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			return (User) dao.searchUserByUserName(connection, userName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	public List<User> fetchAllUser(){
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			return dao.fetchAllUser(connection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	// 通过session获取user信息，若没有信息则返回登录界面
	public User getUserBySession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("title", "登录过期");
			request.setAttribute("state", "登录有效期超时，请重新登录");
			request.setAttribute("nextWeb", "login.jsp");
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
		}
		return user;
	}

	public User login(String userName, String password) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			return dao.searchUserLogin(connection, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	//更改密码
	public void changePasswd(Integer uid,String newPassword) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			connection.setAutoCommit(false);
			dao.changePassword(connection, newPassword, uid);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		
	}
	
	//更改会员的发布权限
	public void changeUserPostRight(User user) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnectionFromPool();
			connection.setAutoCommit(false);
			if(user.getPostRight()==0)user.setPostRight(1);
			else user.setPostRight(0);
			dao.updateUser(connection, user);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	//更改会员的身份权限
		public void changeUserAdministratorRight(User user) {
			Connection connection = null;
			try {
				connection = jdbcTools.getConnectionFromPool();
				connection.setAutoCommit(false);
				if(user.getIsAdministrator()==0)user.setIsAdministrator(1);
				else user.setIsAdministrator(0);
				dao.updateUser(connection, user);
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcTools.releaseResource(null, connection);
			}
		}
}
