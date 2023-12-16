package controller.admin;

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
import model.MGWindow;
import model.User;
import view.customer.CustomerList;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;
import view.admin.*;

public class AdminListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static AdminList adminList = new AdminList();
	
	
	static AdminMenuList adminMenuList = new AdminMenuList();
	static AdminUserList adminUserList = new AdminUserList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Menu")) {
        	getAdminMenuList(s, borderPane, position);
        } else if(option.equals("User")) {
        	getAdminUserList(s, borderPane, position);
        }
	}
	
	public static void displayAdminMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		adminList.display(MGWindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button allMenuBtn, Button home, Button viewOrderBtn, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
		allMenuBtn.setOnAction(
				e -> {
					displayAdminMenu("Menu");;
				}	
		);
		
		viewOrderBtn.setOnAction(
				e -> {
					displayAdminMenu("User");
				}	
		);
		
	    home.setOnAction(
	    		e -> {
	    			
	            	AdminDefaultController.goToAdminDefault();
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

	public static void getAdminMenuList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All Menu ");
		
			StackPane contents = adminMenuList.display(s);
	//		DISINIII
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

	public static void getAdminUserList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All User ");
			
			StackPane contents = adminUserList.display(s);
			
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}
	
}

