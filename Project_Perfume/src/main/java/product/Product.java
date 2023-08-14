package product;


	import java.util.Date;

	public class Product {
		private int prodnum;
		private String name;
		private int price;
		private int stock;
		private String info;
		private String image;
		private int prodgender;
		private String prodans1;
		private String prodans2;
		private Date regdate;
		private String detail;
		
		
		
		public int getProdnum() {
			return prodnum;
		}
		public void setProdnum(int prodnum) {
			this.prodnum = prodnum;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		
		public int getProdgender() {
			return prodgender;
		}
		public void setProdgender(int prodgender) {
			this.prodgender = prodgender;
		}
		public String getProdans1() {
			return prodans1;
		}
		public void setProdans1(String prodans1) {
			this.prodans1 = prodans1;
		}
		
		
		public String getProdans2() {
			return prodans2;
		}
		public void setProdans2(String prodans2) {
			this.prodans2 = prodans2;
		}
		
		public Date getRegdate() {
			return regdate;
		}
		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}
		
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		
		
		
		@Override
		public String toString() {
			return "Product [prodnum=" + prodnum + ", name=" + name + ", price=" + price + 
					", stock=" + stock + ", info=" + info + ", image=" + image + 
					", prodgender=" + prodgender + ", prodans1=" + prodans1 + 
					", prodans2=" + prodans2 + ", regdate=" + regdate + ", detail=" + detail+"]";
		}
		
	   
		
	}