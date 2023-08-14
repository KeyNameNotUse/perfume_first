package order;

import java.util.Date;

public class Order {
	private String ordernum;
	private String id;
	private String name;
	private String tel;
	private String zipcode;
	private String address;
	private int result;
	private int delivercost;
	private Date regdate;
	
	
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getDelivercost() {
		return delivercost;
	}
	public void setDelivercost(int delivercost) {
		this.delivercost = delivercost;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "orderItem [ordernum=" + ordernum + ", name=" + name + ", tel=" + tel + ", zipcode=" + zipcode
				+ ", address=" + address + ", result=" + result + ", delivercost=" + delivercost + ", regdate="
				+ regdate + "]";
	}
}
