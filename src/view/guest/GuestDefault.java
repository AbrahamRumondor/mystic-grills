package view.guest;

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
import view.MGWindow;
import controller.UserController.*;

public class GuestDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static UserController userController = new UserController();
	
	public void display(Stage s) {
		
		MGWindow window = MGWindow.setWindow(s);
		
		StackPane root = window.root;
		Scene scene = window.scene;
		
		
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
		
		
		BorderPane borderPane = new BorderPane();
		
		
		Label restaurantName = new Label("WELCOME TO MYSTIC GRILLS");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		restaurantName.setFont(font);
		
		HBox header = new HBox();
		
//		 define header
		header.getChildren().addAll(restaurantName);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
		
//		set borderpane ke stackpane
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
		
//		set button back ke stackpane
		Button back = new Button("Back");
        StackPane.setMargin(back, new Insets(12,10,10,10));
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        
		
		Button login = new Button("Log In");
		Button signup = new Button("Sign Up");
		
//		 untuk login dan signup
		HBox tengah = new HBox();
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(login, signup);
		tengah.setSpacing(100);
		
		borderPane.setCenter(tengah);
		
		activityLog.add(tengah);
		
	
//     define semua action button          
        addAction(login, back, signup, s, scene, borderPane);
        
//        masukin semuanya ke stackpane
        root.getChildren().addAll(borderPane, back);
		
        
        show(scene, s);
	}
	
	private void addAction(Button login, Button back, Button signup, Stage s, Scene scene, BorderPane borderPane) {
		login.setOnAction(
				e -> {
					
					activityLog.add(userController.displayGuestLogin());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
        			
                	show(scene, s);
				}	
		);
		
		signup.setOnAction(
				e -> {
					activityLog.add(userController.displayGuestSignup());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
        			
                	show(scene, s);
				}	
		);
		
        back.setOnAction(
        		e -> {
        			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
        			if(activityLog.getSceneStack().size() > 1) 
        				activityLog.pop();
        			
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
