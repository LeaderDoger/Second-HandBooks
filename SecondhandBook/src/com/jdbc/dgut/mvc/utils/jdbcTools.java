package com.jdbc.dgut.mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class jdbcTools {
	private static ComboPooledDataSource cpds = null;
	public static Connection getConnectionFromPool() throws Exception {
		if(cpds==null) {
			cpds = new ComboPooledDataSource("com.dgut.javaweb");
		}	
		return cpds.getConnection();
	}
	
	
	
	public static Connection getConnection() throws Exception {
//		String driverClass="com.mysql.cj.jdbc.Driver"; 
//		String jdbcUrl="jdbc:mysql://localhost:3306/jdbc?severTimezone=HongKong";
//		String user = "root";
//		String password="";
//		
//		//注册驱动
//		try {
//			Class.forName(driverClass);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//连接数据库,获得connection
//		Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
//		return connection;
		return getConnectionFromPool();
	}
	public static void releaseResource(Statement statement,Connection connection){	
		try {
			if(statement!=null) {
				statement.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void releaseResource(ResultSet resultSet,Statement statement,Connection connection){
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(statement!=null) {
				statement.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
