package com.dgut.mvc.bean;

public class BookSearchCondition {
	private String id;
	private String fromUid;
	private String author;
	private String bookName;

	public BookSearchCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookSearchCondition(String id, String fromUid, String author, String bookName) {
		super();
		this.id = id;
		this.fromUid = fromUid;
		this.author = author;
		this.bookName = bookName;
	}

	public String getId() {
		if (id == null || id.trim().equals(""))
			return "%%";
		else
			return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromUid() {
		if (fromUid == null || fromUid.trim().equals(""))
			return "%%";
		else
			return fromUid;
	}

	public void setFromUid(String fromUid) {
		this.fromUid = fromUid;
	}

	public String getAuthor() {
		if (author == null || author.trim().equals(""))
			return "%%";
		else
			return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookName() {
		if (bookName == null || bookName.trim().equals(""))
			return "%%";
		else
			return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		return "BookSearchCondition [id=" + id + ", fromUid=" + fromUid + ", author=" + author + ", bookName="
				+ bookName + "]";
	}

}
