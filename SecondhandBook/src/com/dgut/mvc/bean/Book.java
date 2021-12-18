package com.dgut.mvc.bean;

public class Book {

	private Integer id;
	private String bookName;
	private Integer fromUid;
	private String author;
	private String postTime;
	private String context;
	private Integer isPost;
	private Integer isComment;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer id, Integer fromUid, String bookName, String author, String postTime, String context,
			Integer isPost, Integer isComment) {
		super();
		this.id = id;
		this.fromUid = fromUid;
		this.author = author;
		this.bookName = bookName;
		this.postTime = postTime;
		this.context = context;
		this.isPost = isPost;
		this.isComment = isComment;
	}

	public Book(Integer id, Integer fromUid, String bookName, String author, String postTime, String context) {
		super();
		this.id = id;
		this.fromUid = fromUid;
		this.author = author;
		this.bookName = bookName;
		this.postTime = postTime;
		this.context = context;
		isPost = 0;
		isComment = 1;
	}

	public Book(Integer fromUid, String bookName, String author, String postTime, String context) {
		super();
		this.fromUid = fromUid;
		this.author = author;
		this.bookName = bookName;
		this.postTime = postTime;
		this.context = context;
		isPost = 0;
		isComment = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromUid() {
		return fromUid;
	}

	public void setFromUid(Integer fromUid) {
		this.fromUid = fromUid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getContext() {
		if (context == null || context.trim().equals(""))
			return "%%";
		else
			return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getIsPost() {
		return isPost;
	}

	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", fromUid=" + fromUid + ", author=" + author + ", bookName=" + bookName
				+ ", postTime=" + postTime + ", context=" + context + ", isPost=" + isPost + ", isComment=" + isComment
				+ "]";
	}

}
