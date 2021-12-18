package com.dgut.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgut.mvc.bean.Book;
import com.dgut.mvc.bean.BookSearchCondition;
import com.dgut.mvc.bean.Comment;
import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.BookService;
import com.dgut.mvc.service.CommentService;
import com.dgut.mvc.service.UserService;

@WebServlet("*.book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = null;
	private BookService bookService = null;

	public BookServlet() {
		super();
		userService = new UserService();
		bookService = new BookService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - 5);
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

	// 进入书籍搜索页面
	public void searchBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> books = bookService.getAllBooks();
		request.setAttribute("books", books);
		request.getRequestDispatcher("WEB-INF/views/searchBook.jsp").forward(request, response);
	}

	// 对书籍进行搜索
	public void searchBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String fromUid = request.getParameter("fromUid");
		String author = request.getParameter("author");
		String bookName = request.getParameter("bookName");
		BookSearchCondition bc = new BookSearchCondition(id, fromUid, author, bookName);
		List<Book> books = bookService.SearchBooksByCondition(bc);
		request.setAttribute("books", books);
		request.getRequestDispatcher("WEB-INF/views/bookInput.jsp").forward(request, response);
	}

	// 进入提交\发布书籍出售信息页面
	public void upBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userService.getUserBySession(request, response);
		if (user == null)
			return;

		List<Book> books = bookService.getUserUnpostBooks(user.getUid());
		request.setAttribute("unPostBooks", books);
		request.getRequestDispatcher("WEB-INF/views/upBooks.jsp").forward(request, response);
	}

	// 发布二手出售书籍信息
	public void postBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.getUserBySession(request, response);
		if(user==null)return ;
		
		if(user.getPostRight()==1) {
			bookService.PostBook(id);
			upBooks(request, response);
		}
		else {
			request.setAttribute("title", "发布权限错误");
			request.setAttribute("state", "您没有发布消息的权限");
			request.setAttribute("nextWeb", "upBooks.book");
			request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
		}
	}

	// 撤回已发布的二手出售书籍信息
	public void revokeBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		bookService.revokeBook(id);
		new UserServlet().userSpace(request, response);
	}

	// 进入书籍修改页面
	public void editBookInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookService.searchBook(id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("WEB-INF/views/editBookInput.jsp").forward(request, response);
	}

	// 对二手书籍信息进行修改
	public void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookService.searchBook(id);
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String context = request.getParameter("context");
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setContext(context);

		bookService.editBook(book);
	}

	// 提交二手书籍信息
	public void uploadBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String context = request.getParameter("context");
		User user = userService.getUserBySession(request, response);
		if (user == null)
			return;

		Book book = new Book(user.getUid(), bookName, author, null, context);
		bookService.uploadBook(book);
		upBooks(request, response);
	}
	
	//进入书籍页面
	public void bookSpace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取书籍信息
		int id = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.searchBook(id);
		request.setAttribute("book", book);
		
		//获取评论信息
		List<Comment> comments=new CommentService().fetchBookAllComment(id);
		//筛选留言
		List<Comment> commentsTop=new ArrayList<Comment>();
		HashMap<Integer,List<Comment>> replys=new HashMap<Integer,List<Comment>>();
		HashMap<Integer,String> userNames=new HashMap<Integer,String>();
		for(Comment comment : comments) {
			if(userNames.get(comment.getUid())==null) {
				int uid=comment.getUid();
				userNames.put(uid,userService.searchUser(uid).getUserName());
			}
			if(comment.getParentId()==null) {
				commentsTop.add(comment);
			}
			else {
				if(replys.get(comment.getParentId())==null) {
					//如果list还未初始化
					replys.put(comment.getParentId(),new ArrayList<Comment>() );
				}
				replys.get(comment.getParentId()).add(comment);
			}
		}
		request.setAttribute("userNames", userNames);
		request.setAttribute("comments", commentsTop);
		request.setAttribute("replys", replys);
		
		request.getRequestDispatcher("/bookSpace.jsp").forward(request, response);
	}


	public void changeBookCommentRight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		Book book=bookService.searchBook(bookId);
		
		bookService.changeBookCommentRight(book);
		new UserServlet().index(request, response);
	}



}
