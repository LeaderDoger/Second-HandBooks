package com.dgut.mvc.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.Comment;
import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.BookService;
import com.dgut.mvc.service.CommentService;
import com.dgut.mvc.service.UserService;

public class Daotest {
	
	@Test
	void testlogin() {
		String user_name="xujunbin";
		String user_password="8823503Gg";
		
		UserService service = new UserService();
		User user = service.login(user_name, user_password);
		System.out.println(user);
	}
	
	@Test
	void testGetUserAllBooks() {
		String user_name="AAA";
		String user_password="123";
		
		BookService bs=new 	BookService();
		List<Book> books=bs.getUserUnpostBooks(2);
		for(Book book:books)
			System.out.println(book);
	}
	
	@Test
	void testPostBook() {
		BookService bs=new BookService();
		bs.PostBook("2");
		List<Book> books=bs.getUserAllBooks(2);
		for(Book book:books)
			System.out.println(book);
	}
	
	
	@Test
	void testRevokeBook() {
		BookService bs=new BookService();
		bs.revokeBook("2");
		List<Book> books=bs.getUserAllBooks(2);
		for(Book book:books)
			System.out.println(book);
	}
	
	@Test
	void testchangeUserRight() {
		UserService us=new UserService();
		User user=us.searchUser(3);
		us.changeUserPostRight(user);
		List<User> users=us.fetchAllUser();
		for(User temp:users)
			System.out.println(temp);
	}
	
	@Test
	void testfetchBookAllComment() {
		CommentService cs=new CommentService();
		List<Comment> comments=cs.fetchBookAllComment(2);
		for(Comment comment:comments)
			System.out.println(comment);
	}
	
	@Test
	void testSearUser() {
		UserService us=new UserService();
		System.out.println(us.searchUser(1));
	}
}
