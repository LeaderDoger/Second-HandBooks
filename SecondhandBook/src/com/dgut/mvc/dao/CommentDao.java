package com.dgut.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dgut.mvc.bean.Comment;

public interface CommentDao extends DAO<Comment> {
	public void addComment(Connection connection,Comment comment) throws SQLException;
	
	public void deleteComment(Connection connection,int commentId) throws SQLException;

	public List<Comment> fetchComments(Connection connection,int bookId) throws SQLException;

}
