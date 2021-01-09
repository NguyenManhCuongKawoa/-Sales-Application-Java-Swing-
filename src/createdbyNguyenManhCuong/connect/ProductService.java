package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import createdbyNguyenManhCuong.model.ProductInformation;

public class ProductService {
	
	public static Connection connection = SQLServerConnection.getConnection("DESKTOP-GBDCA67\\NGUYENMANHCUONG", "MyProjectBasketballShop");
	
	public static ProductInformation getProduct(int id) {
		ProductInformation product = new ProductInformation();
		try {
			String sql = "select * from ProductInformation where id=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, id);
			ResultSet res = preStatement.executeQuery();
			if(res.next()) {
				product.setId(id);
				product.setProductName(res.getString("name"));
				product.setProductTrademark(res.getString("trademark"));
				product.setProductType(res.getString("type"));
				product.setProductPrice(res.getLong("price"));
				product.setProductAmount(res.getLong("amount"));
				product.setProductDescribe(res.getString("describe"));
				product.setProductImage(res.getBytes("image"));
				return product;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static boolean deleteProduct(int id) {
		try {
			String sql = "delete from ProductInformation where id=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, id);

			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static boolean updateProduct(int id, ProductInformation product) {
		try {
			String sql = "update ProductInformation set name=?, trademark=?, type=?, price=?, amount=?, describe=?, image=?	 where id=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setNString(1, product.getProductName());
			preStatement.setString(2, product.getProductTrademark());
			preStatement.setString(3, product.getProductType());
			preStatement.setLong(4, product.getProductPrice());
			preStatement.setLong(5, product.getProductAmount());
			preStatement.setNString(6, product.getProductDescribe());
			preStatement.setBytes(7, product.getProductImage());
			preStatement.setInt(8, id);

			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static boolean insertProduct(ProductInformation product) {
		try {
			String sql = "insert into ProductInformation values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setNString(1, product.getProductName());
			preStatement.setString(2, product.getProductTrademark());
			preStatement.setString(3, product.getProductType());
			preStatement.setLong(4, product.getProductPrice());
			preStatement.setLong(5, product.getProductAmount());
			preStatement.setNString(6, product.getProductDescribe());
			preStatement.setBytes(7, product.getProductImage());
			int x = preStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public static ArrayList<ProductInformation> getAllProduct() {
		ArrayList<ProductInformation> arrProduct = new ArrayList<ProductInformation>();
		try {
			String sql = "select * from ProductInformation";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			ResultSet res = preStatement.executeQuery();
			while(res.next()) {
				ProductInformation product = new ProductInformation();
				product.setId(res.getInt("id"));
				product.setProductName(res.getString("name"));
				product.setProductTrademark(res.getString("trademark"));
				product.setProductType(res.getString("type"));
				product.setProductPrice(res.getLong("price"));
				product.setProductAmount(res.getLong("amount"));
				product.setProductDescribe(res.getString("describe"));
				product.setProductImage(res.getBytes("image"));
			    arrProduct.add(product);
			}
			return arrProduct;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<ProductInformation> getProductFromName(String name) {
		ArrayList<ProductInformation> arrProduct = new ArrayList<ProductInformation>();
		try {
			String sql = "select * from ProductInformation where name LIKE ?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setNString(1, "%" + name + "%");
			ResultSet res = preStatement.executeQuery();
			while(res.next()) {
				ProductInformation product = new ProductInformation();
				product.setId(res.getInt("id"));
				product.setProductName(res.getString("name"));
				product.setProductTrademark(res.getString("trademark"));
				product.setProductType(res.getString("type"));
				product.setProductPrice(res.getLong("price"));
				product.setProductAmount(res.getLong("amount"));
				product.setProductDescribe(res.getString("describe"));
				product.setProductImage(res.getBytes("image"));
			    arrProduct.add(product);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return arrProduct;
	}
	public static ArrayList<ProductInformation> getAllProductFromType(String type) {
		ArrayList<ProductInformation> arrProduct = new ArrayList<ProductInformation>();
		try {
			String sql = "select * from ProductInformation where type=?";
			PreparedStatement preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, type);
			ResultSet res = preStatement.executeQuery();
			while(res.next()) {
				ProductInformation product = new ProductInformation();
				product.setId(res.getInt("id"));
				product.setProductName(res.getString("name"));
				product.setProductTrademark(res.getString("trademark"));
				product.setProductType(res.getString("type"));
				product.setProductPrice(res.getLong("price"));
				product.setProductAmount(res.getLong("amount"));
				product.setProductDescribe(res.getString("describe"));
				product.setProductImage(res.getBytes("image"));
			    arrProduct.add(product);
			}
			return arrProduct;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
