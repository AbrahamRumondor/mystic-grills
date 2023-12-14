package controller.chef;

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
import view.customer.CustomerMenu;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;
import view.chef.ChefMenu;
import view.chef.ChefOrderDetailList;
import view.chef.ChefOrderList;

public class ChefMenuController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static ChefMenu chefMenu = new ChefMenu();

	static ChefOrderList chefOrderList = new ChefOrderList();
	static ChefOrderDetailList chefOrderDetailList = new ChefOrderDetailList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        	getChefOrderList(s, borderPane);
	}
	
	public static void displayChefMenu(String option) {
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

//	walaupun order, ini ditaro disini karena CustomerMenu bisa pilih untuk menampilkan menulist atau menampilkan MyOrder
//	public static void addCustomerOrderAction(Button onGoingOrder, Label position, BorderPane borderPane, Stage s) {
//		onGoingOrder.setOnAction(
//	    		e -> {
//	    			position.setText("On-Going Order ");
//	    			
//	    			StackPane contents = customerOrderList.display(s);
//	    			
//	    			borderPane.setCenter(contents);
//	    			
//	    			activityLog.add(contents);
//	    		}	
//	    ); 
//	}


	public static void getChefOrderList(Stage s, BorderPane borderPane) {
			StackPane contents = chefOrderList.display(s, borderPane);
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

}
