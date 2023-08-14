package board;

import java.util.Date;

public class Board {
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBoardid() {
		return boardid;
	}
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	private String boardid;
	private String name;
	private String pass;
	private String subject;
	private String content;
	private String image;
	private Date regdate;
	private int readcnt;
	
	@Override
	public String toString() {
		return "board [num=" + num + ", boardid=" + boardid + ", name=" + name + ", pass=" + pass + ", subject="
				+ subject + ", content=" + content + ", image=" + image + ", regdate=" + regdate + ", readcnt="
				+ readcnt + "]";
	}
}
