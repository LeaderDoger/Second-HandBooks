package com.dgut.mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dgut.mvc.bean.Comment;
import com.dgut.mvc.dao.CommentDao;
import com.dgut.mvc.dao.DAO;

public class CommentDaoJdbcImpl extends DaoJdbcImpl<Comment> implements CommentDao {

	@Override
	public void addComment(Connection connection, Comment comment) throws SQLException {
		String sql="INSERT INTO comment(bookId,context,uid,parentId,date) VALUES (?,?,?,?,?)";
		update(connection,sql,
		comment.getBookid(),comment.getContext(),comment.getUid(),comment.getParentId(),comment.getDate());
	}

	@Override
	public void deleteComment(Connection connection, int commentId) throws SQLException {
		String sql="DELETE FROM commemt WHERE commentId=?";
		update(connection,sql,commentId);
	}

	@Override
	public List<Comment> fetchComments(Connection connection, int bookId) throws SQLException {
		String sql="SELECT commentId,bookId,context,uid,parentId,date FROM comment WHERE bookId=?";
		return fetchList(connection, sql, bookId);
	}

	
}
