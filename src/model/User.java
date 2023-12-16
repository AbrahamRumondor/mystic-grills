package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

	private Integer userId;
	private String userRole;
	private String userName;
	private String userEmail;
	private String userPassword;
	
	public User(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public static ArrayList<User>getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		
		String query = "SELECT * FROM user";
		
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("user_id"));
				String name = rs.getString("user_name");
				String email = rs.getString("user_email");
				String password = rs.getString("user_password");
				String role = rs.getString("user_role");
				
				
				users.add(new User(id, role, name, email, password));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

	public static boolean createUser( String role, String name, String email, String password ) {
		
		if(!validateUserCreation(email)) return false;
		
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`user` (`user_name`, `user_email`, `user_password`, `user_role`) VALUES (?, ?, ?, ?);";
		
		System.out.println(name + " " + password);
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setString(1, name);
			prep.setString(2, email);
			prep.setString(3, password);
			prep.setString(4, role);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
		
	}

	private static boolean validateUserCreation(String email) {
		
		ArrayList<User> users = getAllUsers();
		
		for (User user : users) {
			if(user.userEmail.equalsIgnoreCase(email)) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public static User authenticateUser(String email, String password ) {
		
		ArrayList<User> users = getAllUsers();
		
		for (User user : users) {
			if(user.userEmail.equalsIgnoreCase(email) && user.userPassword.equals(password)) {
				return user;
			}
		}
	
		return null;
	}

	public static User getUserById(Integer userId) {
		
		ArrayList<User> users = getAllUsers();
		
		for (User user : users) {
			if(user.userId == userId) {
				return user;
			}
		}
	
		return null;
		
	}
	
	public static String getUserNameById(Integer userId) {
		
		ArrayList<User> users = getAllUsers();
		
		for (User user : users) {
			if(user.userId == userId) {
				return user.getUserName();
			}
		}
	
		return null;
	}
	
	public static void deleteUser(Integer userId) {
		String query = "DELETE FROM `mystic_grills`.`user` WHERE (`user_id` = ?);";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, userId);;
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void updateUser(Integer id, String role, String name, String email, String password) {
		String query = "UPDATE `mystic_grills`.`user` SET `user_name` = ?, `user_email` = ?, `user_password` = ?, `user_role` = ? WHERE (`user_id` = ?);";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setString(1, name);
			prep.setString(2, email);
			prep.setString(3, password);
			prep.setString(4, role);
			prep.setInt(5, id);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}	
	
}
