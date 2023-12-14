package controller.chefwaiter;

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
import model.Order;
import model.User;
import view.MGWindow;
import view.chefwaiter.ChefWaiterMenu;
import view.chefwaiter.ChefWaiterOrderDetailList;
import view.chefwaiter.ChefWaiterOrderList;
import view.customer.CustomerMenu;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;

public class ChefWaiterMenuController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        	getChefOrderList(s, borderPane);
	}
	
	public static void displayChefMenu(String option) {
		ChefWaiterMenu chefMenu = new ChefWaiterMenu();
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		chefMenu.display(MGWindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button orderBtn, Button home, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
		orderBtn.setOnAction(
				e -> {
	    			getChefOrderList(s, borderPane);
				}	
		);
		
		
	    home.setOnAction(
	    		e -> {
	    			getChefOrderList(s, borderPane);
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

	public static void getChefOrderList(Stage s, BorderPane borderPane) {
			ChefWaiterOrderList chefOrderList = new ChefWaiterOrderList();	
			StackPane contents = chefOrderList.display(s, borderPane);
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

}
