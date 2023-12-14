package view.cashier;

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
import controller.MGWindowController;
import controller.UserController.*;
import controller.admin.AdminDefaultController;
import controller.cashier.CashierDefaultController;
import controller.customer.CustomerDefaultController;

public class CashierDefault {
	
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
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
		
		borderPane.setBottom(userRoleLbl);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
		
		HBox header = new HBox();
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
		
//		set borderpane ke stackpane
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));

		Button viewOrder = new Button("View Orders");
		Button viewReceipt = new Button("View Receipts");
		
//		 untuk login dan signup
		HBox tengah = new HBox();
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(viewOrder, viewReceipt);
		tengah.setSpacing(100);
		
		borderPane.setCenter(tengah);
		activityLog.add(tengah);
		
//     define semua action button          
        CashierDefaultController.addAction(viewOrder, viewReceipt, s, scene, borderPane);
        
//        masukin semuanya ke stackpane
        root.getChildren().addAll(borderPane);

        show(scene, s);
	}
	
	public static void show(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}

