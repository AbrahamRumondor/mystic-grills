package view.customer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ActivityLog;
import model.User;
import view.MGWindow;
import controller.WindowController;
import controller.UserController.*;

public class CustomerMenu {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static WindowController windowController = WindowController.getInstance();
	
	private static CustomerMenuList customerMenuList = new CustomerMenuList();
	
	
	public void display(Stage s) {
		
		MGWindow window = WindowController.setWindow(s);
		
		StackPane root = window.root;
		Scene scene = window.scene;
		
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		
		Label userNameLbl = new Label("Welcome, " + userName);
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		
//		set header
		HBox header = new HBox();
		
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
		borderPane.setTop(header);
		
		HBox topButtonBox = new HBox();
		
		topButtonBox.setMaxSize(250, 50);

		Button allMenuBtn = new Button("All Menu");
		Button viewOrderBtn = new Button("My Order");
		Button logOutBtn = new Button("Log Out");
		
		topButtonBox.setAlignment(Pos.TOP_RIGHT);
		topButtonBox.setSpacing(20);
		
		topButtonBox.getChildren().addAll(allMenuBtn, viewOrderBtn, logOutBtn);
		
        StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		
        
//        set contents
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
		
//		set button back ke stackpane
		Button home = new Button("Home");
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
        
		
		Button allMenu = new Button("All Menu");
		Button orderedMenu = new Button("Ordered Menu");
		
//		 untuk login dan signup
		StackPane contents = customerMenuList.display(s);
//		DISINIII
		borderPane.setCenter(contents);
		
		activityLog.add(contents);
		
	
//     define semua action button          
        addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane);
        
//        masukin semuanya ke stackpane
        root.getChildren().addAll(borderPane, home, topButtonBox);
		
        root.setStyle("-fx-background-color: #f4f4f4;");
        
        show(scene, s);
	}
	
	private static void addAction(Button allMenuBtn, Button home, Button viewOrderBtn, Stage s, Scene scene, BorderPane borderPane) {
		allMenuBtn.setOnAction(
				e -> {
					windowController.displayCustomerMenu();
				}	
		);
		
		viewOrderBtn.setOnAction(
				e -> {
//					activityLog.add(userController.displayGuestSignup());
//					borderPane.setCenter(activityLog.getSceneStack().lastElement());
//        			
//                	show(scene, s);
				}	
		);
		
        home.setOnAction(
        		e -> {
        			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
        			User user = UserController.getCurrentUser();
	            	WindowController.goToMainMenu(user);
        		}	
        ); 
        
	}
	
	public static void show(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
