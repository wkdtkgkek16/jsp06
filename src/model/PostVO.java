package model;

import java.util.Date;

public class PostVO {
	private int id;
	private String title;
	private String body;
	private String writer;
	private Date date;
	private String fmtDate;
	
	public String getFmtDate() {
		return fmtDate;
	}
	public void setFmtDate(String fmtDate) {
		this.fmtDate = fmtDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "postVO [id=" + id + ", title=" + title + ", body=" + body + ", writer=" + writer + ", date=" + date
				+ "]";
	}
	
	
}
