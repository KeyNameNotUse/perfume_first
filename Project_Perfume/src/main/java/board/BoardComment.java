package board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardComment {
	private int ser;
	private int num;
	private String name;
	private String content;
	private Date regdate;
	private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public int getSer() {
		return ser;
	}
	public void setSer(int ser) {
		this.ser = ser;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "BoardComment [ser=" + ser + ", num=" + num + ", content=" + content + ", regdate=" + regdate + ", sd="
				+ sd + "]";
	}
	
	
}
