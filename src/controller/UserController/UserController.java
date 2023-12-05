package controller.UserController;

import java.util.ArrayList;

import javafx.scene.Node;
import model.User;
import view.Notification;
import view.guest.GuestLogin;
import view.guest.GuestSignup;
import javafx.scene.layout.BorderPane;

public class UserController {
	private User currentUser;
	
	private GuestSignup guestSignup = new GuestSignup();
	private GuestLogin guestLogin = new GuestLogin();
	
//	NOTE: ASUMSI MVC CONTROLLER DISINI MODEL HANYA MENANGANI LOGIC UNTUK KE DATABASE
//		  SEHINGGA HAL HAL TERKAIT VALIDASI DILUAR ITU, DILAKUKAN DI CONTROLLER
	
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
//				show error message
				 Notification.showErrorMessage("Email already registered");
				 return;
			}
			
			 Notification.showErrorMessage("Account created successfully");
	}
	
	public static void authenticateUser(String email, String password ) {
		
		if(email.isEmpty() || password.isEmpty()) {
			 Notification.showErrorMessage("Please fill all blank fields");	
			 return;
		} else if(!email.endsWith("@gmail.com")) {
			 Notification.showErrorMessage("Email must ends with @gmail.com");	
			 return;
		} else if(password.length() < 6) {
			 Notification.showErrorMessage("Password must be at least 6 chars long");
			 return;
		}
		
		User userValidity = User.authenticateUser(email, password);
		if(userValidity != null) {
//			go to main mystic grill window
			System.out.println("ANJAY JADI NGAB");
			return;
		}
		
		 Notification.showErrorMessage("Incorrect email address or password");
		 return;
	}
	
	
	
	public Node displayGuestLogin() {
		return guestLogin.display();
	}
	
	public Node displayGuestSignup() {
		return guestSignup.display();
	}
	
	
	
	
}
