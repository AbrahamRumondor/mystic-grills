package controller.UserController;

import java.util.ArrayList;

import javafx.scene.Node;
import model.User;
import view.MGWindow;
import view.Notification;
import view.guest.GuestLogin;
import view.guest.GuestSignup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserController {
	private static User currentUser;
	
	private static UserController userController;
	
	private GuestSignup guestSignup = new GuestSignup();
	private GuestLogin guestLogin = new GuestLogin();
	
//	NOTE: ASUMSI MVC CONTROLLER DISINI MODEL HANYA MENANGANI LOGIC UNTUK KE DATABASE
//		  SEHINGGA HAL HAL TERKAIT VALIDASI DILUAR ITU, DILAKUKAN DI CONTROLLER
	
//	memastikan usercontroller hanya boleh ada satu, why? karena kita hanya butuh 1 user controller,
//	selain itu, adanya usercontroller baru artinya currentUsernya berbeda.
	public static UserController getInstance() {
		if(userController == null) {
			synchronized (UserController.class) {
				if(userController==null)userController = new UserController();
			}
		}
		return userController;
	}
	
	
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
	
	public static User getUserById(Integer userid) {
		return User.getUserById(userid);
	}
	
	
	public Node displayGuestLogin() {
		return guestLogin.display();
	}
	
	public Node displayGuestSignup() {
		return guestSignup.display();
	}


	public User getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(User currentuser) {
		currentUser = currentuser;
	}
	
	
	
	
}
