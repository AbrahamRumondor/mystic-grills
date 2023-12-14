package controller.guest;

import controller.OrderController;
import controller.UserController.UserController;
import controller.admin.AdminDefaultController;
import controller.cashier.CashierDefaultController;
import controller.chefwaiter.ChefWaiterMenuController;
import controller.customer.CustomerDefaultController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ActivityLog;
import model.User;
import view.guest.GuestLogin;
import view.guest.GuestSignup;
import view.guest.GuestDefault;

public class GuestController {
	
//	Diputuskan bahwa yang dimasukkan di controller hanyalah yang berhubungan dengan logic diluar view.
//	Sehingga untuk logic seperti setting posisi buttons dll itu tetap ditaro di view
//	Tujuannya agar dapat terlihat dengan jelas bagaimana view tersebut dibentuk.
	
//	Untuk guest, hanya dibuat 1 controller karena tidak terlalu banyak logic pada tiap classnya,
//	sehingga baiknya digabung saja.
	
	private static GuestSignup guestSignup = new GuestSignup();
	private static GuestLogin guestLogin = new GuestLogin();
	private static GuestDefault guestDefault = new GuestDefault();
		
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
//	routing 
	
	public static Node displayGuestLogin() {
		return guestLogin.display();
	}
	
	public static Node displayGuestSignup() {
		return guestSignup.display();
	}
	
	public static void displayGuestDefault(Stage s) {
		guestDefault.display(s);
	}
	
	
//	button actions defaultguest
	public static void addDefaultGuestAction(Button login, Button back, Button signup, Stage s, Scene scene, BorderPane borderPane) {
		login.setOnAction(
				e -> {
					activityLog.add(GuestController.displayGuestLogin());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
				}	
		);
		
		signup.setOnAction(
				e -> {
					activityLog.add(GuestController.displayGuestSignup());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
				}	
		);
		
        back.setOnAction(
        		e -> {
        			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
        			if(activityLog.getSceneStack().size() > 1) 
        				activityLog.pop();	
        			borderPane.setCenter(activityLog.getSceneStack().lastElement());
        		}	
        ); 
        
	}
	
//	untuk guestlogin
	public static void addGuestLoginAction(Button submit, TextField emailTf, PasswordField passwordPf) {
		submit.setOnAction(
        		e -> {
        			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
        			// disini nanti view notification akan dipanggil.
    	            Integer validityId = UserController.authenticateUser(emailTf.getText(),passwordPf.getText());
    	            
    	            if(validityId != null) {
    	            	User user = UserController.getUserById(validityId);
    	            	OrderController.setOngoingOrder(user);
    	            	UserController.setCurrentUser(user);
    	            	
    	            	if(user.getUserRole().equals("Customer"))
    	            		CustomerDefaultController.goToCustomerDefault();
    	            	else if(user.getUserRole().equals("Admin"))
    	            		AdminDefaultController.goToAdminDefault();
    	            	else if(user.getUserRole().equals("Cashier"))
    	            		CashierDefaultController.goToCashierDefault();
    	            	else if(user.getUserRole().equals("Chef") || 
    	            			user.getUserRole().equals("Waiter"))
    	            		ChefWaiterMenuController.displayChefMenu("Order");
    	            }
        		}	
        ); 
		
	}
	
//	untuk guestsignup
	public static void addGuestSignupAction(Button submit, TextField nameTf, TextField emailTf, PasswordField passwordPf, PasswordField confirmPf) {
        submit.setOnAction(
			e -> {
				// disini nanti view notification akan dipanggil.
	            UserController.createUser("Customer", nameTf.getText(), emailTf.getText(), passwordPf.getText(), confirmPf.getText());
			}	
		);
	}
	
}
