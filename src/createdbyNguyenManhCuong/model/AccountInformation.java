package createdbyNguyenManhCuong.model;

import java.util.Date;

public class AccountInformation {
	private int id;
	private String accountName, password;
	private String userName, gender, phone, address;
	private Date dateOfBirth;
	private byte[] accountImage;
	
	public AccountInformation() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
    public byte[] getAccountImage() {
		return accountImage;
	}

	public void setAccountImage(byte[] accountImage) {
		this.accountImage = accountImage;
	}

	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "Account Name: " + accountName + " \n" +
    			"Password: " + password +" \n" +
    	        "UserName: " + userName + " \n" +
    			"Gender: " + gender + " \n" +
    	        "Phone: " + phone + " \n" +
    			"Address: " + address + " \n" +
    	        "Date of birth: " + String.valueOf(dateOfBirth) + 
    	        "\nImage: " + accountImage.toString();
    }
}
