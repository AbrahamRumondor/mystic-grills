package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME = "ooad_lab"; // sesuaikan dengan nama database admin
	private final String PASSWORD = "mystic-grills"; // sesuaikan dengan password database admin
	private final String DATABASE = "mystic_grills)";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	private static Connect connect;

	private Connect() {
		try {  
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);  
			st = con.createStatement(); 
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect the database, the system is terminated!");
			System.exit(0);
		}  
	}

	public static synchronized Connect getConnection() {
		if(connect == null) {
			synchronized (Connect.class) {
				if(connect==null)connect = new Connect();
			}
		}
		return connect;
	}
		
	public ResultSet executeStatementQuery(String query) {
		rs = null;
		try {
	        rs = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void executeStatementUpdate(String query) {
	    try {
	        st.executeUpdate(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public PreparedStatement prepare(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	public ResultSet executePreparedQuery(PreparedStatement ps) {
		rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void executePreparedUpdate(PreparedStatement ps) {
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
