package survey;

import java.util.Date;

public class Survey {
	
	private String id; 			// id
	private int ser; 			// serial?
	private int ansGender; 		// 1남자 2여자
	private String ans1; 		// 선호향 1과일 2꽃 3나무 
	private String ans2; 		// 선택지 4가지 최대 2개저장 가능  // 문자열 길어서 db varchar 50으로 조절
	private String prodname;
	private int doughnut;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSer() {
		return ser;
	}
	public void setSer(int ser) {
		this.ser = ser;
	}
	public int getAnsGender() {
		return ansGender;
	}
	public void setAnsGender(int ansGender) {
		this.ansGender = ansGender;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public int getDoughnut() {
		return doughnut;
	}
	public void setDoughnut(int doughnut) {
		this.doughnut = doughnut;
	}
	@Override
	public String toString() {
		return "Survey [id=" + id + ", ser=" + ser + ", ansGender=" + ansGender + ", ans1=" + ans1 + ", ans2=" + ans2
				+ ", prodname=" + prodname + ", doughnut=" + doughnut + "]";
	}
	
	


	
	
	
	
	
	
	
	
}