package com.dgut.mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.BookSearchCondition;
import com.dgut.mvc.dao.BookDao;
import com.dgut.mvc.daoImpl.BookDaoJdbcImpl;
import com.jdbc.dgut.mvc.utils.jdbcTools;

public class BookService {
	private BookDao dao;

	public BookService() {
		super();
		this.dao = new BookDaoJdbcImpl();
	}

	//提交二手书籍消息
	public void uploadBook(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.addBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// 通过id查询二手书籍消息
	public Book searchBook(int id) {
		Connection connection = null;
		Book book = null;
		try {
			connection = jdbcTools.getConnection();
			book = dao.searchBook(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return book;
	}

	// 修改二手书籍消息
	public void editBook(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.updateBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// 搜索所有已发布的二手书籍信息
	public List<Book> getAllBooks() {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchPostedAllBook(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// 搜索该用户已提交或发布的二手书籍信息
	public List<Book> getUserAllBooks(Integer uid) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchUserAllBook(connection, uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// 搜索该用户未发布的二手书籍信息
	public List<Book> getUserUnpostBooks(Integer uid) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.fetchUserUnpostBook(connection, uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// 通过书籍信息模糊搜索
	public List<Book> SearchBooksByCondition(BookSearchCondition conditions) {
		Connection connection = null;
		List<Book> books = new ArrayList<Book>();
		try {
			connection = jdbcTools.getConnection();
			books = dao.searchBooksByCondition(connection, conditions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
		return books;
	}

	// 发布书籍出售信息
	public void PostBook(String id) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.setBookIsPost(connection, id, true);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}

	// 撤回书籍出售信息
	public void revokeBook(String id) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			dao.setBookIsPost(connection, id, false);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	//
	public void changeBookCommentRight(Book book) {
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			connection.setAutoCommit(false);
			if(book.getIsComment()==0)book.setIsComment(1);
			else book.setIsComment(0);
			dao.updateBook(connection, book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcTools.releaseResource(null, connection);
		}
	}
	
	
}
