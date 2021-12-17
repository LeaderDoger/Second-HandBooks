package com.dgut.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.BookSearchCondition;

public interface BookDao extends DAO<Book> {
	public void addBook(Connection connection, Book book) throws SQLException;

	public void deleteBook(Connection connection, String bookName) throws SQLException;

	public void deleteBook(Connection connection, Integer id) throws SQLException;

	public void updateBook(Connection connection, Book book) throws SQLException;

	public Book searchBook(Connection connection, int id) throws SQLException;

	public List<Book> fetchPostedAllBook(Connection connection) throws SQLException;

	public List<Book> fetchUserAllBook(Connection connection, Integer uid) throws SQLException;

	public List<Book> fetchUserUnpostBook(Connection connection, Integer uid) throws SQLException;

	public List<Book> searchBooksByCondition(Connection connection, BookSearchCondition condition) throws SQLException;

	public void setBookIsPost(Connection connection, String id, boolean state) throws SQLException;

	public void setBookIsComment(Connection connection, String id, boolean state) throws SQLException;

}
