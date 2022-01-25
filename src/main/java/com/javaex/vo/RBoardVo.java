package com.javaex.vo;

public class RBoardVo {

	private int no;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int userNo;
	private String writer;
	private int groupNo;
	private int orderNo;
	private int depth;
	
	public RBoardVo() {
	}

	public RBoardVo(int no, String title, String content, int hit, String regDate, int userNo, String writer,
			int groupNo, int orderNo, int depth) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.writer = writer;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "rBoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", writer=" + writer + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + "]";
	}


	
	
}
