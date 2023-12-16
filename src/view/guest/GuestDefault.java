package view.guest;

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
import controller.MGWindowController;
import controller.guest.GuestDefaultController;

public class GuestDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public void display(Stage s) {
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();	
		Label restaurantName = new Label("WELCOME TO MYSTIC GRILLS");
		GuestDefaultController.setLabelFont(restaurantName);
		
		HBox headerBox = new HBox();
		GuestDefaultController.createHeaderBox(restaurantName, headerBox);
		
//		set button back ke stackpane
		Button back = new Button("Back");
		
		Button login = new Button("Log In");
		Button signup = new Button("Sign Up");
		
//		 set hbox dan itemsnya.
		HBox centerBox = new HBox();
		GuestDefaultController.createCenterBox(login, signup, centerBox);
		
		GuestDefaultController.setBorderpane(borderPane, headerBox, centerBox);
		activityLog.add(centerBox);
		
//     define semua action button          
        GuestDefaultController.addDefaultGuestAction(login, back, signup, s, scene, borderPane);
//     set stackpane items & masukin semuanya ke stackpane
        GuestDefaultController.setStackpaneItems(borderPane, back, root);
        
        GuestDefaultController.showGuestDefault(scene, s);
	}
}
