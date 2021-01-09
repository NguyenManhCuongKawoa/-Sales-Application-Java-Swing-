package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import createdbyNguyenManhCuong.model.AccountInformation;

public class SignUpService {
	public static Connection connection = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	public static void signUp(AccountInformation account) {
		try {
			String sql = "INSERT INTO AccountInformation (accountName, password, userName, gender, phone, dateOfBirth, address, image) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			//preStatement.setInt(1, account.getId());
			preStatement.setString(1, account.getAccountName());
			preStatement.setString(2, account.getPassword());
			preStatement.setString(3, account.getUserName());
			preStatement.setString(4, account.getGender());
			preStatement.setString(5, account.getPhone());
			preStatement.setDate(6, (Date) account.getDateOfBirth());
			preStatement.setString(7, account.getAddress());
			preStatement.setBytes(8, account.getAccountImage());
			int x = preStatement.executeUpdate();
			if(x > 0) JOptionPane.showMessageDialog(null, "Sign Up Success!");
			else JOptionPane.showMessageDialog(null, "Sign Up Error!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
