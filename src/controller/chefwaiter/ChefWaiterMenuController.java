package controller.chefwaiter;

import controller.MGWindowController;
import controller.guest.GuestDefaultController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ActivityLog;
import model.MGWindow;
import model.User;
import view.chefwaiter.ChefWaiterMenu;
import view.chefwaiter.ChefWaiterOrderList;

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
	    			
	    			GuestDefaultController.displayGuestDefault(s);
	    		}	
	    ); 
	    
	}

	public static void getChefOrderList(Stage s, BorderPane borderPane) {
			ChefWaiterOrderList chefOrderList = new ChefWaiterOrderList();	
			StackPane contents = chefOrderList.display(s, borderPane);
			borderPane.setCenter(contents);
			
			activityLog.add(contents);
		}

//	configure view
	public static void configureBorderpane(BorderPane borderPane, HBox headerPane) {
		borderPane.setTop(headerPane);
	}

	public static void setRootStackpane(StackPane root, BorderPane borderPane, HBox topButtonPane, Button home) {
		root.getChildren().addAll(borderPane, home, topButtonPane);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	public static void defineTopButtonPane(Label position, Button allOrder, Button logOutBtn, HBox topButtonPane) {
		topButtonPane.setMaxSize(450, 50);
		topButtonPane.setAlignment(Pos.TOP_RIGHT);
		topButtonPane.setSpacing(20);
		topButtonPane.getChildren().addAll(position, allOrder, logOutBtn);
	}

	public static String getCurrentPosition(User user, String pos) {
		if(user.getUserRole().equals("Chef"))
			pos = "All Pending Order ";
		else if(user.getUserRole().equals("Waiter"))
			pos = "All Prepared Order";
		return pos;
	}

	public static void defineHeaderPane(Label userNameLbl, HBox headerPane) {
		headerPane.getChildren().addAll(userNameLbl);
		headerPane.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
	}

	public static void setLabelFont(Label userNameLbl, Label position) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		position.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	public static void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void showChefWaiterMenu(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
}
