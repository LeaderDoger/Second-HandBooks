package com.dgut.mvc.dao;

import com.dgut.mvc.bean.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
	public void addUser(Connection connection, User User) throws SQLException;

	public void deleteUser(Connection connection, String userName) throws SQLException;

	public void deleteUser(Connection connection, Integer uid) throws SQLException;
	
	public void updateUser(Connection connection, User User) throws SQLException;

	public User searchUser(Connection connection, int uid) throws SQLException;
	
	public User searchUserByUserName(Connection connection, String userName) throws SQLException;

	public User searchUserLogin(Connection connection, String userName, String password) throws SQLException;

	public void changePassword(Connection connection, String newpassword, Integer uid) throws SQLException;
	
	public List<User> fetchAllUser(Connection connection) throws SQLException;
	
//	public List<User> fetchAllUsers(Connection connection) 
//			throws SQLException;

}
