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
import model.MGWindow;
import model.User;
import controller.MGWindowController;
import controller.UserController.*;
import controller.customer.CustomerDefaultController;

public class CustomerDefault {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();	
	
	public void display(Stage s) {
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		String userRole = UserController.getCurrentUser().getUserRole();
		
		Label userNameLbl = new Label("Welcome, " + userName );
		Label userRoleLbl = new Label("Role: " + userRole);
		setLabelFont(userNameLbl, userRoleLbl);
				
		HBox headerBox = new HBox();
		createHeaderBox(borderPane, userNameLbl, headerBox);
		
//		untuk login dan signup
		Button allMenu = new Button("All Menu");
		Button orderedMenu = new Button("Ordered Menu");
		
		HBox centerBox = new HBox();
		createCenterBox(allMenu, orderedMenu, centerBox);
		activityLog.add(centerBox);
		
//		set isi borderpane dan posisi stackpanenya	
		setBorderpane(borderPane, userRoleLbl, centerBox);
			
//		define semua action button          
        CustomerDefaultController.addAction(allMenu, orderedMenu, s, scene, borderPane);
        
//      masukin semuanya ke stackpane
        setRootStackpane(root, borderPane);

        showCustomerDefault(scene, s);
	}

	private void setRootStackpane(StackPane root, BorderPane borderPane) {
		root.getChildren().addAll(borderPane);
	}

	private void setBorderpane(BorderPane borderPane, Label userRoleLbl, HBox tengah) {
		borderPane.setBottom(userRoleLbl);
		borderPane.setCenter(tengah);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
	}

	private void setLabelFont(Label userNameLbl, Label userRoleLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
	}

	private void createHeaderBox(BorderPane borderPane, Label userNameLbl, HBox header) {
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
	}

	private void createCenterBox(Button allMenu, Button orderedMenu, HBox tengah) {
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(allMenu, orderedMenu);
		tengah.setSpacing(100);
	}
	
	public static void showCustomerDefault(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
