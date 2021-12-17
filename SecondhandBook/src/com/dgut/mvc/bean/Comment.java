package com.dgut.mvc.bean;

public class Comment {
	private Integer commentId;
	private Integer bookid;
	private String context;
	private Integer uid;
	private Integer parentId;
	private String date;
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer commentId, Integer bookid, String context, Integer uid, Integer parentId, String date) {
		super();
		this.commentId = commentId;
		this.bookid = bookid;
		this.context = context;
		this.uid = uid;
		this.parentId = parentId;
		this.date = date;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", bookid=" + bookid + ", context=" + context + ", uid=" + uid
				+ ", parentId=" + parentId + ", date=" + date + "]";
	}
	
}

