package controller.cashier;

import controller.WindowController;
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
import view.admin.*;
import view.cashier.CashierOrderList;
import view.cashier.CashierList;

public class CashierListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static CashierList cashierList = new CashierList();
	
	
//	static AdminMenuList adminMenuList = new AdminMenuList();
	static CashierOrderList cashierOrderList = new CashierOrderList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Order")) {
        	getCashierOrderList(s, borderPane, position);
        } else if(option.equals("Receipt")) {
        	getAdminUserList(s, borderPane, position);
        }
	}
	
	public static void displayOrderMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		cashierList.display(WindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button orderBtn, Button home, Button receiptBtn, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
		orderBtn.setOnAction(
				e -> {
					displayOrderMenu("Order");;
				}	
		);
		
		receiptBtn.setOnAction(
				e -> {
					displayOrderMenu("Receipt");
				}	
		);
		
	    home.setOnAction(
	    		e -> {
	    			
	            	CashierDefaultController.goToCashierDefault();
	    		}	
	    ); 
	    
	    logOutBtn.setOnAction(
	    		e -> {
	    			WindowController.activityLog.getSceneStack().removeAllElements();
	    			MGWindow.getWindow().root.getChildren().clear();
	    			
	    			GuestController.displayGuestDefault(s);
	    		}	
	    ); 
	    
	}

	public static void getCashierOrderList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All Order ");
		
			StackPane contents = cashierOrderList.display(s);
	//		DISINIII
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

	public static void getAdminUserList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All User ");
			
			StackPane contents = cashierOrderList.display(s);
			
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}
	
}

