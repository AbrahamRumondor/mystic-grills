package view.cashier;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.MGWindow;
import controller.MGWindowController;
import controller.cashier.CashierListController;
import controller.model.UserController;

public class CashierList {
	
	public static MGWindowController windowController = MGWindowController.getInstance();	
	
	public void display(Stage s, String option) {
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		Label userNameLbl = new Label("Welcome, " + userName);
		
//		set header
		HBox headerPane = new HBox();
		CashierListController.createHeaderPane(userNameLbl, headerPane);
				
		Label position = new Label();
		Button viewOrderBtn = new Button("View Orders");
		Button viewReceiptBtn = new Button("View Receipts");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonPane = new HBox();
		CashierListController.createTopButtonPane(position, viewOrderBtn, viewReceiptBtn, logOutBtn, topButtonPane);
		
		CashierListController.setLabelFont(userNameLbl, position);
		CashierListController.configureBorderpane(borderPane, headerPane);
		
		Button home = new Button("Home");
		CashierListController.setStackpane(borderPane, topButtonPane, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        CashierListController.getDisplay(option, s, borderPane, position);

//     	define semua action button          
        CashierListController.addAction(viewOrderBtn, home, viewReceiptBtn, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        CashierListController.setRootStackpane(root, borderPane, topButtonPane, home);
        CashierListController.showCashierList(scene, s);
	}
}
