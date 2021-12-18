package com.dgut.mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.BookSearchCondition;
import com.dgut.mvc.dao.BookDao;

public class BookDaoJdbcImpl extends DaoJdbcImpl<Book> implements BookDao {

	@Override
	public void addBook(Connection connection, Book book) throws SQLException {
		String sql = "INSERT INTO book(fromUid,author,bookName,postTime,context,isPost,isComment) VALUES (?,?,?,?,?,?,?)";
		update(connection, sql, book.getFromUid(), book.getAuthor(), book.getBookName(), book.getPostTime(),
				book.getContext(),book.getIsPost(),book.getIsComment());
	}

	@Override
	public void deleteBook(Connection connection, String bookName) throws SQLException {
		String sql = "DELETE FROM book WHERE bookName=?";
		update(connection, sql, bookName);
	}

	@Override
	public void deleteBook(Connection connection, Integer id) throws SQLException {
		String sql = "DELETE FROM book WHERE id=?";
		update(connection, sql, id);
	}

	@Override
	public void updateBook(Connection connection, Book book) throws SQLException {
		String sql = "UPDATE book SET fromUid=?, author=?,bookName=?,postTime=?,context=?,isPost=?,isComment=? WHERE id=?";
		update(connection, sql, book.getFromUid(), book.getAuthor(), book.getBookName(), book.getPostTime(),
				book.getContext(),book.getIsPost(),book.getIsComment(), book.getId());
	}
	
	//查找书籍出售信息
	@Override
	public Book searchBook(Connection connection, int id) throws SQLException {
		String sql = "SELECT id,fromUid,author,bookName,postTime,context,isPost,isComment FROM book WHERE id=?";
		return fetch(connection, sql, id);
	}
	
	//查找已发布的书籍出售信息
	@Override
	public List<Book> fetchPostedAllBook(Connection connection) throws SQLException {
		String sql = "SELECT id,fromUid,author,bookName,postTime,context,isPost,isComment FROM book WHERE isPost=1";
		return fetchList(connection, sql, null);
	}
	
	//查找用户的书籍信息(已发布和未发布)
	@Override
	public List<Book> fetchUserAllBook(Connection connection,Integer uid) throws SQLException {
		String sql = "SELECT id,fromUid,author,bookName,postTime,context,isPost,isComment FROM book WHERE fromUid=?";
		return fetchList(connection, sql, uid);
	}
	
	//查找用户未发布的书籍信息
		@Override
		public List<Book> fetchUserUnpostBook(Connection connection,Integer uid) throws SQLException {
			String sql = "SELECT id,fromUid,author,bookName,postTime,context FROM book WHERE fromUid=? AND isPost=0";
			return fetchList(connection, sql, uid);
		}
	
	//搜索已发布的书籍出售信息
	@Override
	public List<Book> searchBooksByCondition(Connection connection, BookSearchCondition condition) throws SQLException {
		String sql="SELECT id,fromUid,author,bookName,postTime,context,isPost FROM book WHERE "
				+ "id 		like ? AND "
				+ "fromUid 	like ? AND "
				+ "author 	like ? AND "
				+ "bookName	like ? AND "
				+"isPost=1";
		Object[] objs= {
				condition.getId(),
				condition.getFromUid(),
				condition.getAuthor(),
				condition.getBookName()
		};
		return fetchList(connection,sql,objs);
	}

	//修改书籍的发布状态
	public void setBookIsPost(Connection connection,String id,boolean state) throws SQLException {
		//撤回与发布
		if(state==false) {
			String sql="UPDATE book SET isPost=?,postTime=null WHERE id=?";
			update(connection, sql, state,id);
		}else {
			String sql="UPDATE book SET isPost=?,postTime=? WHERE id=?";
			update(connection, sql, state,new Timestamp(new java.util.Date().getTime()),id);
		}
		
	}
	//修改书籍的留言状态状态
	public void setBookIsComment(Connection connection,String id,boolean state) throws SQLException {
		String sql="UPDATE book SET isComment=? WHERE id=?";
		update(connection, sql, state,id);
	}
	
}
