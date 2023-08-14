package member;

import java.util.Date;

public class Member {
	
	private String id;
	private String pass;
	private String name;
	private int gender;
	private String tel;
	private String email;
	private String zipcode;
	private String address;
	private Date rdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pass=" + pass + ", name=" + name + ", gender=" + gender + ", tel=" + tel
				+ ", email=" + email + ", zipcode=" + zipcode + ", address=" + address + ", rdate=" + rdate + "]";
	}
	

}