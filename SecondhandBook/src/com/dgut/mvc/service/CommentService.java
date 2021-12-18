package com.dgut.mvc.service;

import java.sql.Connection;
import java.util.List;

import com.dgut.mvc.bean.Comment;
import com.dgut.mvc.dao.CommentDao;
import com.dgut.mvc.daoImpl.CommentDaoJdbcImpl;
import com.jdbc.dgut.mvc.utils.jdbcTools;

public class CommentService {
	private CommentDao dao;

	public CommentService() {
		super();
		this.dao = new CommentDaoJdbcImpl();
	}

	// 添加评价\回复
	public void addComment(Comment comment) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.addComment(connection, comment);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	//获取书的所有留言和回复
	public List<Comment> fetchBookAllComment(int bookId) {
		Connection connection = null;
		List<Comment> comments=null;
		try {
			connection = jdbcTools.getConnection();
			comments=dao.fetchComments(connection,bookId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return comments;
	}
	
	
}
