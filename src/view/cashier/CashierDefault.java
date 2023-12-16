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
import model.MGWindow;
import model.User;
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
		setLabelFont(userNameLbl, userRoleLbl);
		
		HBox headerPane = new HBox();
		createHeaderPane(userNameLbl, headerPane);

		Button viewOrder = new Button("View Orders");
		Button viewReceipt = new Button("View Receipts");
		HBox centerPane = new HBox();
		createCenterPane(viewOrder, viewReceipt, centerPane);
		
		configureBorderpane(borderPane, userRoleLbl, headerPane, centerPane);
		activityLog.add(centerPane);
		
//     define semua action button          
        CashierDefaultController.addAction(viewOrder, viewReceipt, s, scene, borderPane);
        
		setRootStackpane(root, borderPane);
        showCashierDefault(scene, s);
	}

	private void setRootStackpane(StackPane root, BorderPane borderPane) {
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        root.getChildren().addAll(borderPane);
	}

	private void configureBorderpane(BorderPane borderPane, Label userRoleLbl, HBox headerPane, HBox centerPane) {
		borderPane.setTop(headerPane);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(userRoleLbl);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
	}

	private void createCenterPane(Button viewOrder, Button viewReceipt, HBox centerPane) {
		centerPane.setAlignment(Pos.CENTER);
		centerPane.getChildren().addAll(viewOrder, viewReceipt);
		centerPane.setSpacing(100);
	}

	private void createHeaderPane(Label userNameLbl, HBox headerPane) {
		headerPane.getChildren().addAll(userNameLbl);
		headerPane.setAlignment(Pos.CENTER);
	}

	private void setLabelFont(Label userNameLbl, Label userRoleLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
	}
	
	public static void showCashierDefault(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}

