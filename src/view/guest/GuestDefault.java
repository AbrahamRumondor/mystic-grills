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
import model.MGWindow;
import controller.MGWindowController;
import controller.UserController.*;
import controller.guest.GuestController;

public class GuestDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public void display(Stage s) {
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();	
		Label restaurantName = new Label("WELCOME TO MYSTIC GRILLS");
		setLabelFont(restaurantName);
		
		HBox headerBox = new HBox();
		createHeaderBox(restaurantName, headerBox);
		
//		set button back ke stackpane
		Button back = new Button("Back");
		
		Button login = new Button("Log In");
		Button signup = new Button("Sign Up");
		
//		 set hbox dan itemsnya.
		HBox centerBox = new HBox();
		createCenterBox(login, signup, centerBox);
		
		setBorderpane(borderPane, headerBox, centerBox);
		activityLog.add(centerBox);
		
//     define semua action button          
        GuestController.addDefaultGuestAction(login, back, signup, s, scene, borderPane);
        
//     set stackpane items & masukin semuanya ke stackpane
		setStackpaneItems(borderPane, back, root);
        
        showGuestDefault(scene, s);
	}

	private void setBorderpane(BorderPane borderPane, HBox headerBox, HBox centerBox) {
		borderPane.setTop(headerBox);
		borderPane.setCenter(centerBox);
	}

	private void createCenterBox(Button login, Button signup, HBox centerBox) {
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(login, signup);
		centerBox.setSpacing(100);
	}

	private void setLabelFont(Label restaurantName) {
		restaurantName.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	private void createHeaderBox(Label restaurantName, HBox headerBox) {
		headerBox.getChildren().addAll(restaurantName);
		headerBox.setAlignment(Pos.CENTER);
	}
	
//	set stackpane
	public static void setStackpaneItems(BorderPane borderPane, Button back, StackPane root) {
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(back, new Insets(12,10,10,10));
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        root.getChildren().addAll(borderPane, back);
	}
	
	public static void showGuestDefault(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
