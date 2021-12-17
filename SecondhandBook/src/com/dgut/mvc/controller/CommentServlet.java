package com.dgut.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.Comment;
import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.BookService;
import com.dgut.mvc.service.CommentService;
import com.dgut.mvc.service.UserService;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet(name = "commentServlet", urlPatterns = { "*.comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=null;
	private CommentService commentService=null;
	private BookServlet bookServlet=null;
	private BookService bookService=null;
	public CommentServlet() {
		super();
		userService=new UserService();
		commentService=new CommentService();
		bookServlet=new BookServlet();
		bookService=new BookService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - 8);
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void addComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=userService.getUserBySession(request, response);
		if(user==null)return;
		int bookid=Integer.parseInt(request.getParameter("bookId"));
		
		Book book=bookService.searchBook(bookid);
		
		if(book.getIsComment()==1) {
			int uid=user.getUid();
			String context=request.getParameter("context");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=dateFormat.format(Calendar.getInstance().getTime());
			
			Comment comment=new Comment(null,bookid,context,uid,null,date);
			commentService.addComment(comment);

			bookServlet.bookSpace(request, response);
		}
		else {
			request.setAttribute("title", "留言错误");
			request.setAttribute("state", "该书已关闭留言许可");
			request.setAttribute("nextWeb", "bookSpace.book?bookId="+bookid);
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
		}
	}
	
	public void addReply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=userService.getUserBySession(request, response);
		if(user==null)return;
		int bookid=Integer.parseInt(request.getParameter("bookId"));
		Book book=bookService.searchBook(bookid);
		
		if(book.getIsComment()==1) {
			int parentId=Integer.parseInt(request.getParameter("parentId"));
			int uid=user.getUid();
			String context=request.getParameter("context"+parentId);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=dateFormat.format(Calendar.getInstance().getTime());
			
			Comment comment=new Comment(null,bookid,context,uid,parentId,date);
			commentService.addComment(comment);

			bookServlet.bookSpace(request, response);
		}
		else {
			request.setAttribute("title", "留言错误");
			request.setAttribute("state", "该书已关闭留言许可");
			request.setAttribute("nextWeb", "bookSpace.book?bookId="+bookid);
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
		}
	}
	
	public void deleteComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
