package common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelperClass {
	public Connection DataBaseConnection() {
		Connection conn = null;
		String dataBase ="jdbc:mysql://localhost:3306/PRODAJA_AUTOMOBILA"; 
		String username = "root";
		String password = "mikimaus147";
	    try {
	    	conn = DriverManager.getConnection(dataBase, username, password);
	        if (conn != null) {
	            DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
	            System.out.println("Driver name: " + dm.getDriverName());
	        }
	        else {
	        	System.out.println("CONNECTION LOST.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return conn;
		}
	
	public Object CenterWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		return null;
	}
}