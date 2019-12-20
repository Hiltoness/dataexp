package dataexp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconn {
	public static Connection getConn(){
		Connection conn=null;
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost/sele?serverTimezone=UTC";
		String user="root";
		String password="";
		try {
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Success connect Mysql server!");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConn(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
				conn = null;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
	public static void main(String args[]) {
       getConn();
    }

}
