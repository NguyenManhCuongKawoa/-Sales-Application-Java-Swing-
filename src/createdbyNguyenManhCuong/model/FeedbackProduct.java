package createdbyNguyenManhCuong.model;

import java.util.Date;

public class FeedbackProduct {
	private int id, accountID, productID, productAssessment;
	private String productComment;
	private Date dateOfAssessment;
	
	
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
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getProductAssessment() {
		return productAssessment;
	}
	public void setProductAssessment(int productAssessment) {
		this.productAssessment = productAssessment;
	}
	public String getProductComment() {
		return productComment;
	}
	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}
	public Date getDateOfAssessment() {
		return dateOfAssessment;
	}
	public void setDateOfAssessment(Date dateOfAssessment) {
		this.dateOfAssessment = dateOfAssessment;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  productID + " | " + accountID + " | " + productAssessment + " | " + productComment + " | " + dateOfAssessment;
	}
}
