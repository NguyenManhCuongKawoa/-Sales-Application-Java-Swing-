package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import createdbyNguyenManhCuong.model.Bill;
import createdbyNguyenManhCuong.model.ProductInformation;



public class BillService {
	public static Connection conn = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	
	public static ArrayList<Bill> getBillFromName(String name) {
		ArrayList<Bill> arrBill = new ArrayList<Bill>();
		try {
			String sql = "select * from Bill where billName LIKE ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setNString(1, "%" + name + "%");
			ResultSet resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Bill bill = new Bill();
				bill.setId(resultSet.getInt("id"));
				bill.setBillName(resultSet.getNString("billName"));
				bill.setAccountID(resultSet.getInt("accountID"));
				bill.setDateOfOrder(resultSet.getDate("dateOfOrder"));
				bill.setNgayNhanHang(resultSet.getDate("ngayNhanHang"));
				bill.setAddressNhanHang(resultSet.getNString("addressNhanHang"));
				bill.setDescribeBill(resultSet.getNString("describeBill"));
				bill.setPhoneBill(resultSet.getString("phoneBill"));
				bill.setActiveBill(resultSet.getInt("activeBill"));
				bill.setAmountProduct(resultSet.getInt("amountProduct"));
				bill.setPriceTotal(resultSet.getInt("priceTotal"));
				arrBill.add(bill);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return arrBill;
	}
	public static boolean updateAmountProduct_PriceTotalFromBill(int billID, int amountProduct, int price) {
		try {
			String sql = "UPDATE Bill SET amountProduct = amountProduct + ?, priceTotal = priceTotal + ? WHERE id = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, amountProduct);
			preStatement.setInt(2, price);
			preStatement.setInt(3, billID);
			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean deleteBill(int billID) {
		try {
			String sql = "DELETE FROM Bill_Product WHERE billID = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, billID);
			int x = preStatement.executeUpdate();
			String sql2 = "DELETE FROM Bill WHERE id = ?";
			PreparedStatement preStatement2 = conn.prepareStatement(sql2);
			preStatement2.setInt(1, billID);
			int x2 = preStatement2.executeUpdate();
			if(x2 > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean createNewBill(Bill bill) {
		try {
			String sql = "INSERT INTO Bill ( billName, accountID, ngayNhanHang, describeBill, addressNhanHang, phoneBill) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setNString(1, bill.getBillName());
			preStatement.setInt(2, bill.getAccountID());
			preStatement.setDate(3, bill.getNgayNhanHang());
			preStatement.setNString(4, bill.getDescribeBill());
			preStatement.setNString(5, bill.getAddressNhanHang());
			preStatement.setString(6, bill.getPhoneBill());
			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteProduct(int billID, int productID) {
		try {
			String sql = "DELETE FROM Bill_Product WHERE billID = ? AND productID = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, billID);
			preStatement.setInt(2, productID);
			int x = preStatement.executeUpdate();
			if(x > 0) {
				ProductInformation product = ProductService.getProduct(productID);
				if(BillService.updateAmountProduct_PriceTotalFromBill(billID, -1, (int) ((-1) * product.getProductPrice())) == true) return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean addProductIntoBill(int billID, int productID) {
		try {
			String sql = "INSERT INTO  Bill_Product (billID, productID) VALUES(?, ?)";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, billID);
			preStatement.setInt(2, productID);
			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static ArrayList<Integer> getProductFromBillID(int billID) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		try {
			String sql = "SELECT productID FROM Bill_Product WHERE billID = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, billID);
			ResultSet res = preStatement.executeQuery();
			while(res.next()) {
				int x = res.getInt("productID");
				arr.add(x);
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static Bill getBillFromID(int id) {
		Bill bill = null;
		try {
			String sql = "SELECT * FROM Bill Where id = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, id);
			ResultSet resultSet = preStatement.executeQuery();
			if(resultSet.next()) {
				bill = new Bill();
				bill.setId(resultSet.getInt("id"));
				bill.setBillName(resultSet.getNString("billName"));
				bill.setAccountID(resultSet.getInt("accountID"));
				bill.setDateOfOrder(resultSet.getDate("dateOfOrder"));
				bill.setNgayNhanHang(resultSet.getDate("ngayNhanHang"));
				bill.setAddressNhanHang(resultSet.getNString("addressNhanHang"));
				bill.setDescribeBill(resultSet.getNString("describeBill"));
				bill.setPhoneBill(resultSet.getString("phoneBill"));
				bill.setActiveBill(resultSet.getInt("activeBill"));
				bill.setAmountProduct(resultSet.getInt("amountProduct"));
				bill.setPriceTotal(resultSet.getInt("priceTotal"));
			}
			return bill;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<Bill> GetAllBillFromAccount(int accountID) {
		ArrayList<Bill> arr = new ArrayList<Bill>();
		Bill bill = null;
		try {
			String sql = "SELECT * FROM Bill Where accountID = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, accountID);
			ResultSet resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				bill = new Bill();
				bill.setId(resultSet.getInt("id"));
				bill.setBillName(resultSet.getNString("billName"));
				bill.setAccountID(resultSet.getInt("accountID"));
				bill.setDateOfOrder(resultSet.getDate("dateOfOrder"));
				bill.setNgayNhanHang(resultSet.getDate("ngayNhanHang"));
				bill.setAddressNhanHang(resultSet.getNString("addressNhanHang"));
				bill.setDescribeBill(resultSet.getNString("describeBill"));
				bill.setPhoneBill(resultSet.getString("phoneBill"));
				bill.setActiveBill(resultSet.getInt("activeBill"));
				bill.setAmountProduct(resultSet.getInt("amountProduct"));
				bill.setPriceTotal(resultSet.getInt("priceTotal"));
				arr.add(bill);
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static ArrayList<Bill> GetAllBillIsActived(int activeBill) {
		ArrayList<Bill> arr = new ArrayList<Bill>();
		Bill bill = null;
		try {
			String sql = "SELECT * FROM Bill Where activeBill = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, activeBill);
			ResultSet resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				bill = new Bill();
				bill.setId(resultSet.getInt("id"));
				bill.setBillName(resultSet.getNString("billName"));
				bill.setAccountID(resultSet.getInt("accountID"));
				bill.setDateOfOrder(resultSet.getDate("dateOfOrder"));
				bill.setNgayNhanHang(resultSet.getDate("ngayNhanHang"));
				bill.setAddressNhanHang(resultSet.getNString("addressNhanHang"));
				bill.setDescribeBill(resultSet.getNString("describeBill"));
				bill.setPhoneBill(resultSet.getString("phoneBill"));
				bill.setActiveBill(resultSet.getInt("activeBill"));
				bill.setAmountProduct(resultSet.getInt("amountProduct"));
				bill.setPriceTotal(resultSet.getInt("priceTotal"));
				arr.add(bill);
			}
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static boolean setActiveBill(int activeBill, int BillID) {
		try {
			String sql = "UPDATE Bill SET activeBill = ? WHERE id = ?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, activeBill);
			preStatement.setInt(2, BillID);
			int res = preStatement.executeUpdate();
			if(res > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
