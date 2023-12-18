package controller.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.Node;
import model.Connect;
import model.MGWindow;
import model.User;
import view.guest.GuestLogin;
import view.guest.GuestSignup;
import view.popup.Notification;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserController {
	private static User currentUser;
	
//	NOTE: ASUMSI MVC CONTROLLER DISINI MODEL HANYA MENANGANI LOGIC UNTUK KE DATABASE
//		  SEHINGGA HAL HAL TERKAIT VALIDASI DILUAR ITU, DILAKUKAN DI CONTROLLER
	
//	controller model
	public static void createUser(String userRole, String userName, String userEmail, String userPassword, String confirmPassword) {
		if(userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
			Notification.showErrorMessage("Please fill all fields");
			return;
		} else if(!userEmail.endsWith("@gmail.com")) {
			Notification.showErrorMessage("Email must ends with @gmail.com");
			return;
		} else if(userPassword.length() < 6) {
			 Notification.showErrorMessage("Password must be at least 6 chars long");
			 return;
		} else if (!userPassword.equals(confirmPassword)) {
			 Notification.showErrorMessage("Password didn't match");
			 return;
		}
		
		boolean creation = User.createUser(userRole, userName, userEmail, userPassword);
		
		if(!creation) {
//			show error message
			 Notification.showErrorMessage("Email already registered");
			 return;
		}
		
		 Notification.showErrorMessage("Account created successfully");
}
	
	public static void updateUser(Integer id, String role, String name, String email, String password) {
		User.updateUser(id, role, name, email, password);
	}
	
	public static void deleteUser(Integer userId) {
		User.deleteUser(userId);
	}
	
	public static Integer authenticateUser(String email, String password ) {
		
		if(email.isEmpty() || password.isEmpty()) {
			 Notification.showErrorMessage("Please fill all blank fields");	
			 return null;
		} else if(!email.endsWith("@gmail.com")) {
			 Notification.showErrorMessage("Email must ends with @gmail.com");	
			 return null;
		} else if(password.length() < 6) {
			 Notification.showErrorMessage("Password must be at least 6 chars long");
			 return null;
		}
		
		User userValidity = User.authenticateUser(email, password);
		if(userValidity != null) {
//			go to main mystic grill window
			System.out.println("ANJAY JADI NGAB");
			return userValidity.getUserId();
		}
		
		 Notification.showErrorMessage("Incorrect email address or password");
		 return null;
	}
	
	public static ArrayList<User>getAllUsers() {
		return User.getAllUsers();
	}
			
	public static User getUserById(Integer userid) {
		return User.getUserById(userid);
	}
	
	public static String getUserNameById(Integer userId) {
		return User.getUserNameById(userId);
	}

//	controller current user
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentuser) {
		currentUser = currentuser;
	}
	
}
