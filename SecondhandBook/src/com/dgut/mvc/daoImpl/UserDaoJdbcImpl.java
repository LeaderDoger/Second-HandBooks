package com.dgut.mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dgut.mvc.bean.User;
import com.dgut.mvc.dao.UserDao;

public class UserDaoJdbcImpl extends DaoJdbcImpl<User> implements UserDao {

	@Override
	public void addUser(Connection connection, User user) throws SQLException {
		String sql = "INSERT INTO user(name,password,administrator,postRight) VALUES (?,?,0,1)";
		update(connection, sql, user.getUserName(), user.getPassword());
	}

	@Override
	public void deleteUser(Connection connection, String userName) throws SQLException {
		String sql = "DELETE FROM user WHERE userName=?";
		update(connection, sql, userName);
	}

	@Override
	public void deleteUser(Connection connection, Integer uid) throws SQLException {
		String sql = "DELETE FROM user WHERE uid=?";
		update(connection, sql, uid);
	}
	
	@Override
	public void updateUser(Connection connection, User User) throws SQLException {
		String sql = "UPDATE user SET name=?, password=?,administrator=?,postRight=? WHERE uid=?";
		update(connection, sql, User.getUserName(), User.getPassword(), User.getIsAdministrator(), User.getPostRight(),User.getUid());
	}

	@Override
	public User searchUser(Connection connection, int uid) throws SQLException {
		String sql = "SELECT uid, name userName, password,administrator isAdministrator,postRight FROM user WHERE uid=?";
		return fetch(connection, sql, uid);
	}
	
	@Override
	public User searchUserByUserName(Connection connection, String userName) throws SQLException {
		String sql = "SELECT uid, name userName, password,administrator isAdministrator,postRight FROM user WHERE name=?";
		return fetch(connection, sql, userName);
	}
	@Override
	public User searchUserLogin(Connection connection, String userName, String password) throws SQLException {
		String sql="SELECT uid,name userName,password,administrator isAdministrator,postRight FROM user WHERE name=? AND password=?";
		return fetch(connection, sql, userName, password);
	}
	
	@Override
	public void changePassword(Connection connection, String newpassword, Integer uid) throws SQLException {
		String sql = "UPDATE user SET password=? WHERE uid=?";
		update(connection, sql,newpassword, uid);
	}
	
	@Override
	public List<User> fetchAllUser(Connection connection) throws SQLException {
		String sql = "SELECT uid,name userName,password,administrator isAdministrator,postRight FROM user";
		return fetchList(connection, sql, null);
	}
}