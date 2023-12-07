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
import controller.WindowController;
import controller.UserController.*;
import controller.guest.GuestController;

public class GuestDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public void display(Stage s) {
		
		MGWindow window = WindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();	
		Label restaurantName = new Label("WELCOME TO MYSTIC GRILLS");
		restaurantName.setFont(Font.font(null, FontWeight.BOLD, 20));
		
		HBox header = new HBox();
		header.getChildren().addAll(restaurantName);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
		
//		set button back ke stackpane
		Button back = new Button("Back");
		
		Button login = new Button("Log In");
		Button signup = new Button("Sign Up");
		
//		 set hbox dan itemsnya.
		HBox tengah = new HBox();
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(login, signup);
		tengah.setSpacing(100);
		borderPane.setCenter(tengah);
		
		activityLog.add(tengah);
		
//     define semua action button          
        GuestController.addDefaultGuestAction(login, back, signup, s, scene, borderPane);
        
//     set stackpane items & masukin semuanya ke stackpane
		setStackpaneItems(borderPane, back, root);
        
        show(scene, s);
	}
	
//	set stackpane
	public static void setStackpaneItems(BorderPane borderPane, Button back, StackPane root) {
//		set borderpane ke stackpane
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(back, new Insets(12,10,10,10));
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        root.getChildren().addAll(borderPane, back);
	}
	
	public static void show(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
