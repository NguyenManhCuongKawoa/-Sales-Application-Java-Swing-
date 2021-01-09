package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import createdbyNguyenManhCuong.model.FeedbackProduct;
import createdbyNguyenManhCuong.model.ProductInformation;


public class FeedbackProductOtherService {
	public static Connection conn = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	
	public static ArrayList<FeedbackProduct> getAllFeedbackFromProduct(int productID) {
		ArrayList<FeedbackProduct> arr = new ArrayList<FeedbackProduct>();
		try {
			FeedbackProduct f = null;
			String sql = "SELECT * FROM FeedbackProduct WHERE productID = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, productID);
			ResultSet resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				f = new FeedbackProduct();
				f.setAccountID(resultSet.getInt("accountID"));
				f.setProductID(resultSet.getInt("productID"));
				f.setDateOfAssessment(resultSet.getDate("dateOFAssessment"));
				f.setProductAssessment(resultSet.getInt("productAssessment"));
				f.setProductComment(resultSet.getNString("productComment"));
				arr.add(f);
			}
			return arr;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean insertFeedbackProduct(FeedbackProduct f) {
		try {
			String sql = "INSERT INTO FeedbackProduct (accountID, productID, productAssessment, productComment) VALUES(?, ?, ?, ?)";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, f.getAccountID());
			preStatement.setInt(2, f.getProductID());
			preStatement.setInt(3, f.getProductAssessment());
			preStatement.setNString(4, f.getProductComment());
			int res = preStatement.executeUpdate();
			if(res > 0) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
