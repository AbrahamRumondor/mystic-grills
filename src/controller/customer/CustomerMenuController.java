package controller.customer;

import controller.MGWindowController;
import controller.UserController.UserController;
import controller.guest.GuestController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ActivityLog;
import model.User;
import view.MGWindow;
import view.customer.CustomerMenu;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;

public class CustomerMenuController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static CustomerMenu customerMenu = new CustomerMenu();

	static CustomerMenuList customerMenuList = new CustomerMenuList();
	static CustomerOrderList customerOrderList = new CustomerOrderList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Menu")) {
        	CustomerMenuController.getCustomerMenuList(s, borderPane);
        } else if(option.equals("Order")) {
        	CustomerMenuController.getCustomerOrder(s, borderPane, position);
        }
	}
	
	public static void displayCustomerMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		customerMenu.display(MGWindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button allMenuBtn, Button home, Button viewOrderBtn, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
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
	            	CustomerDefaultController.goToCustomerDefault();
	    		}	
	    ); 
	    
	    logOutBtn.setOnAction(
	    		e -> {
	    			MGWindowController.activityLog.getSceneStack().removeAllElements();
	    			MGWindow.getWindow().root.getChildren().clear();
	    			
	    			GuestController.displayGuestDefault(s);
	    		}	
	    ); 
	    
	}

//	walaupun order, ini ditaro disini karena CustomerMenu bisa pilih untuk menampilkan menulist atau menampilkan MyOrder
	public static void addCustomerOrderAction(Button onGoingOrder, Label position, BorderPane borderPane, Stage s) {
		onGoingOrder.setOnAction(
	    		e -> {
	    			position.setText("On-Going Order ");
	    			
	    			StackPane contents = CustomerMenuController.customerOrderList.display(s);
	    			
	    			borderPane.setCenter(contents);
	    			
	    			CustomerMenuController.activityLog.add(contents);
	    		}	
	    ); 
	}


	public static void getCustomerMenuList(Stage s, BorderPane borderPane) {
			StackPane contents = customerMenuList.display(s);
	//		DISINIII
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

	public static void getCustomerOrder(Stage s, BorderPane borderPane, Label position) {
			
			position.setText("My Order ");
			
			Button onGoingOrder = new Button("On-going Order");
			Button orderHistory = new Button("Order History");
			
	//		 untuk login dan signup
			HBox tengah = new HBox();
			tengah.setAlignment(Pos.CENTER);
			tengah.getChildren().addAll(onGoingOrder, orderHistory);
			tengah.setSpacing(100);
			
			borderPane.setCenter(tengah);
			
			activityLog.add(tengah);
			
			addCustomerOrderAction( onGoingOrder,  position,  borderPane,  s);
		}
	
}
