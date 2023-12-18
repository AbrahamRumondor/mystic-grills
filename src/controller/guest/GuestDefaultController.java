package controller.guest;

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
import view.guest.GuestDefault;

public class GuestDefaultController {
	
	private static GuestDefault guestDefault = new GuestDefault();
	static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void displayGuestDefault(Stage s) {
		guestDefault.display(s);
	}

	//	button actions defaultguest
	public static void addDefaultGuestAction(Button login, Button back, Button signup, Stage s, Scene scene, BorderPane borderPane) {
		login.setOnAction(
				e -> {
					activityLog.add(GuestLoginController.displayGuestLogin());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
				}	
		);
		
		signup.setOnAction(
				e -> {
					activityLog.add(GuestSignupController.displayGuestSignup());
					borderPane.setCenter(activityLog.getSceneStack().lastElement());
				}	
		);
		
	    back.setOnAction(
	    		e -> {
	    			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
	    			if(activityLog.getSceneStack().size() > 1) 
	    				activityLog.pop();	
	    			borderPane.setCenter(activityLog.getSceneStack().lastElement());
	    		}	
	    ); 
	}
	
//  configure view
	public static void setBorderpane(BorderPane borderPane, HBox headerBox, HBox centerBox) {
		borderPane.setTop(headerBox);
		borderPane.setCenter(centerBox);
	}

	public static void createCenterBox(Button login, Button signup, HBox centerBox) {
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(login, signup);
		centerBox.setSpacing(100);
	}

	public static void setLabelFont(Label restaurantName) {
		restaurantName.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	public static void createHeaderBox(Label restaurantName, HBox headerBox) {
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