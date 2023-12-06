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

public class CustomerDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static UserController userController = UserController.getInstance();
	
	private static WindowController windowController = WindowController.getInstance();
	
	
	public void display(Stage s) {
		
		MGWindow window = WindowController.setWindow(s);
		
		StackPane root = window.root;
		Scene scene = window.scene;
		
		
		BorderPane borderPane = new BorderPane();
		
		String userName = userController.getCurrentUser().getUserName();
		String userRole = userController.getCurrentUser().getUserRole();
		
		Label userNameLbl = new Label("Welcome, " + userName );
		Label userRoleLbl = new Label("Role: " + userRole);
		
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
		
		borderPane.setBottom(userRoleLbl);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
		
		HBox header = new HBox();
		
//		 define header
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
		
//		set borderpane ke stackpane
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        
		
		Button allMenu = new Button("All Menu");
		Button orderedMenu = new Button("Ordered Menu");
		
//		 untuk login dan signup
		HBox tengah = new HBox();
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(allMenu, orderedMenu);
		tengah.setSpacing(100);
		
		borderPane.setCenter(tengah);
		
		activityLog.add(tengah);
		
	
//     define semua action button          
        addAction(allMenu, orderedMenu, s, scene, borderPane);
        
//        masukin semuanya ke stackpane
        root.getChildren().addAll(borderPane);
		
        
        show(scene, s);
	}
	
	private static void addAction(Button allMenu, Button orderedMenu, Stage s, Scene scene, BorderPane borderPane) {
		allMenu.setOnAction(
				e -> {	
					windowController.displayCustomerMenu();
				}	
		);
		
		orderedMenu.setOnAction(
				e -> {
					activityLog.add(userController.displayGuestSignup());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
        			
                	show(scene, s);
				}	
		);
		
        
	}
	
	public static void show(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
