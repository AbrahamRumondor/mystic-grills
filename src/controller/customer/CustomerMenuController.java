package controller.customer;

import controller.WindowController;
import controller.UserController.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ActivityLog;
import model.User;
import view.MGWindow;
import view.customer.CustomerMenu;

public class CustomerMenuController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static WindowController windowController;
	private static CustomerMenu customerMenu = new CustomerMenu();

	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Menu")) {
        	CustomerController.getCustomerMenuList(s, borderPane);
        } else if(option.equals("Order")) {
        	CustomerController.getCustomerOrder(s, borderPane, position);
        }
	}
	
	public static void displayCustomerMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		customerMenu.display(WindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button allMenuBtn, Button home, Button viewOrderBtn, Stage s, Scene scene, BorderPane borderPane) {
		allMenuBtn.setOnAction(
				e -> {
					displayCustomerMenu("Menu");;
				}	
		);
		
		viewOrderBtn.setOnAction(
				e -> {
					displayCustomerMenu("Order");
				}	
		);
		
	    home.setOnAction(
	    		e -> {
	    			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
	    			User user = UserController.getCurrentUser();
	            	CustomerDefaultController.goToCustomerDefault(user);
	    		}	
	    ); 
	    
	}
	
}
