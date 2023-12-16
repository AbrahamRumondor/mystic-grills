package controller.admin;

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
	    			
	    			GuestDefaultController.displayGuestDefault(s);
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
	
//	configure view
	public static void setRootStackpane(StackPane root, BorderPane borderPane, HBox topButtonPane, Button home) {
		root.getChildren().addAll(borderPane, home, topButtonPane);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	public static void configureBorderpane(BorderPane borderPane, HBox headerPane) {
		borderPane.setTop(headerPane);
	}

	public static void createTopButtonPane(Label position, Button allMenuBtn, Button viewOrderBtn, Button logOutBtn,
			HBox topButtonPane) {
		topButtonPane.setMaxSize(450, 50);
		topButtonPane.setAlignment(Pos.TOP_RIGHT);
		topButtonPane.setSpacing(20);
		topButtonPane.getChildren().addAll(position, allMenuBtn, viewOrderBtn, logOutBtn);
	}

	public static void createHeaderPane(Label userNameLbl, HBox headerPane) {
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
	
	public static void showAdminList(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
}

