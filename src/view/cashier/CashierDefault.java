package view.cashier;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ActivityLog;
import model.MGWindow;
import controller.MGWindowController;
import controller.cashier.CashierDefaultController;
import controller.model.UserController;

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
		CashierDefaultController.setLabelFont(userNameLbl, userRoleLbl);
		
		HBox headerPane = new HBox();
		CashierDefaultController.createHeaderPane(userNameLbl, headerPane);

		Button viewOrder = new Button("View Orders");
		Button viewReceipt = new Button("View Receipts");
		HBox centerPane = new HBox();
		CashierDefaultController.createCenterPane(viewOrder, viewReceipt, centerPane);
		
		CashierDefaultController.configureBorderpane(borderPane, userRoleLbl, headerPane, centerPane);
		activityLog.add(centerPane);
		
//     define semua action button          
        CashierDefaultController.addAction(viewOrder, viewReceipt, s, scene, borderPane);
        
        CashierDefaultController.setRootStackpane(root, borderPane);
        CashierDefaultController.showCashierDefault(scene, s);
	}
}

