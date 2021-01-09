package createdbyNguyenManhCuong.model;

import java.sql.Date;


public class Bill implements Comparable<Bill> {
	
	public static enum TypeCompare {
		name,
		amount, 
		price,
		macDinh
	}
	public static TypeCompare typeCompare;
	
	private int id, accountID, activeBill, amountProduct, priceTotal;
	private String billName, describeBill, addressNhanHang, phoneBill;
	Date dateOfOrder, ngayNhanHang;
	
	public Bill() {
		super();
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public int getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getDescribeBill() {
		return describeBill;
	}

	public void setDescribeBill(String describeBill) {
		this.describeBill = describeBill;
	}

	public String getAddressNhanHang() {
		return addressNhanHang;
	}

	public void setAddressNhanHang(String addressNhanHang) {
		this.addressNhanHang = addressNhanHang;
	}

	public String getPhoneBill() {
		return phoneBill;
	}

	public void setPhoneBill(String phoneBill) {
		this.phoneBill = phoneBill;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getNgayNhanHang() {
		return ngayNhanHang;
	}

	public void setNgayNhanHang(Date ngayNhanHang) {
		this.ngayNhanHang = ngayNhanHang;
	}

	public int getActiveBill() {
		return activeBill;
	}

	public void setActiveBill(int activeBill) {
		this.activeBill = activeBill;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " | " + billName + " | " + dateOfOrder;
	}
	@Override
	public int compareTo(Bill o) {
		if(typeCompare == TypeCompare.name) { // So Sánh theo Tên Sản Phẩm
			return this.getBillName().compareToIgnoreCase(o.getBillName());
		}
		else if(typeCompare == TypeCompare.amount) { // So Sánh Theo Số Lượng Sản Phẩm
			if(this.getAmountProduct() > o.getAmountProduct()) return 1;
			else if(this.getAmountProduct() < o.getAmountProduct()) return -1;
			else return 0;
		}
		else if(typeCompare == TypeCompare.price) { // So Sánh Theo Price Của Sản Phảm
			if(this.getPriceTotal() > o.getPriceTotal()) return 1;
			else if(this.getPriceTotal() < o.getPriceTotal()) return -1;
			else return 0;
		}
		return 0;
	}
}
