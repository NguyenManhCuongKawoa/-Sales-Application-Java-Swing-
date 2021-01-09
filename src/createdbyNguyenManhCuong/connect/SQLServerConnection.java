package createdbyNguyenManhCuong.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnection {
	public static Connection getConnection(String strServer, String strDatabase) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String strConn = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase +
					         ";integratedSecurity=true;";
			return DriverManager.getConnection(strConn);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
