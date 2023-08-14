package order;

import java.util.Date;
import java.util.List;

public class OrderItem {
	private int oditemnum;
	private String ordernum;
	private int prodnum;
	private int quantity;
	private int price;
	public int getOditemnum() {
		return oditemnum;
	}
	public void setOditemnum(int oditemnum) {
		this.oditemnum = oditemnum;
	}
	
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public int getProdnum() {
		return prodnum;
	}
	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "orderItem [oditemnum=" + oditemnum + ", ordernum=" + ordernum + ", prodnum=" + prodnum + ", quantity="
				+ quantity + ", price=" + price + "]";
	}	
	
}
