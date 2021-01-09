package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import createdbyNguyenManhCuong.model.AccountInformation;

public class LoginService {
	public static Connection connection = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	public static AccountInformation login(String accountName, String password) {
		AccountInformation account = new AccountInformation();
		try {
			String sql = "select * from AccountInformation where accountName=? and password=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, accountName);
			preStatement.setString(2, password);
			ResultSet res = preStatement.executeQuery();
			if(res.next()) {
				account.setId(res.getInt("id")); 
				account.setAccountName(res.getString("accountName"));
				account.setPassword(res.getString("password"));
				account.setUserName(res.getString("userName"));
				account.setGender(res.getString("gender"));
				account.setPhone(res.getString("phone"));
				account.setDateOfBirth(res.getDate("dateOfBirth"));
				account.setAddress(res.getString("address"));
				account.setAccountImage(res.getBytes("image"));
				return account;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static AccountInformation getAccount(int accountID) {
		AccountInformation account = new AccountInformation();
		try {
			String sql = "select * from AccountInformation where id = ?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, accountID);
			ResultSet res = preStatement.executeQuery();
			if(res.next()) {
				account.setId(res.getInt("id")); 
				account.setAccountName(res.getString("accountName"));
				account.setPassword(res.getString("password"));
				account.setUserName(res.getString("userName"));
				account.setGender(res.getString("gender"));
				account.setPhone(res.getString("phone"));
				account.setDateOfBirth(res.getDate("dateOfBirth"));
				account.setAddress(res.getString("address"));
				account.setAccountImage(res.getBytes("image"));
				return account;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static boolean changePassword(int id, String password) {
		try {
			String sql = "update AccountInformation set password = ? where id = ?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, password);
			preStatement.setInt(2, id);
			int change = preStatement.executeUpdate();
			if(change > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static boolean changeInformationAccount(int id, AccountInformation account) {
		try {
			String sql = "update AccountInformation set userName = ?, gender = ?, phone = ?, address = ?, dateOfBirth = ?, image = ? where id = ?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setNString(1, account.getUserName());
			preStatement.setString(2, account.getGender());
			preStatement.setString(3, account.getPhone());
			preStatement.setString(4, account.getAddress());
			preStatement.setDate(5, (Date) account.getDateOfBirth());
			preStatement.setBytes(6, account.getAccountImage());
			preStatement.setInt(7, id);
			int change = preStatement.executeUpdate();
			if(change > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static ArrayList<String> layTatCaCacAccount() {
		ArrayList<String> arrAccount = new ArrayList<String>();
		try {
			String sql = "select * from AccountInformation";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			ResultSet res = preStatement.executeQuery();
			while(res.next()) {
			     arrAccount.add(res.getString("accountName"));
			}
			return arrAccount;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
