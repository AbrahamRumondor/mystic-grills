package controller.guest;

import controller.admin.AdminDefaultController;
import controller.cashier.CashierDefaultController;
import controller.chefwaiter.ChefWaiterMenuController;
import controller.customer.CustomerDefaultController;
import controller.model.OrderController;
import controller.model.UserController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.User;
import view.guest.GuestLogin;

public class GuestLoginController {
	
	private static GuestLogin guestLogin = new GuestLogin();

	public static Node displayGuestLogin() {
		return guestLogin.display();
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

//  configure view
	public static void addGridpane(GridPane gridPane, Label email, Label password, TextField emailTf, PasswordField passwordPf, Button submit) {
		gridPane.add(email, 1, 0); 
		gridPane.add(password, 1, 1);
		gridPane.add(emailTf, 2, 0); 
		gridPane.add(passwordPf, 2, 1);
		gridPane.add(submit, 1, 2);
	}
	
	public static void setGridpane(GridPane gridPane) {
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
	}
}
