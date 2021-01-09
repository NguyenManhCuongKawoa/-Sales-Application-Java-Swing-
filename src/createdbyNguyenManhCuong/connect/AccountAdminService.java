package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;



public class AccountAdminService {
	
	public static Connection connection = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	
	public static boolean updateAccountAvatar(int id, byte[] image) {
		try {
			String sql = "update AccountInformation set image=?	 where id=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setBytes(1, image);
			preStatement.setInt(2, id);
			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
