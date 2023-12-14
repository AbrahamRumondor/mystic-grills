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
import model.Order;
import view.MGWindow;
import controller.MGWindowController;
import controller.UserController.*;
import controller.admin.AdminListController;
import controller.cashier.CashierListController;
import controller.customer.CustomerMenuController;

public class CashierList {
	
	public static MGWindowController windowController = MGWindowController.getInstance();	
	
	public void display(Stage s, String option) {
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		Label userNameLbl = new Label("Welcome, " + userName);
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		
//		set header
		HBox header = new HBox();
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
		
		borderPane.setTop(header);
		
		Label position = new Label();
		position.setFont(Font.font(null, FontWeight.BOLD, 20));
		Button viewOrderBtn = new Button("View Orders");
		Button viewReceiptBtn = new Button("View Receipts");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonBox = new HBox();
		topButtonBox.setMaxSize(450, 50);
		topButtonBox.setAlignment(Pos.TOP_RIGHT);
		topButtonBox.setSpacing(20);
		topButtonBox.getChildren().addAll(position, viewOrderBtn, viewReceiptBtn, logOutBtn);
		
//		set button back ke stackpane
		Button home = new Button("Home");
		
        setStackpane(borderPane, topButtonBox, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        CashierListController.getDisplay(option, s, borderPane, position);

//     	define semua action button          
        CashierListController.addAction(viewOrderBtn, home, viewReceiptBtn, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        root.getChildren().addAll(borderPane, home, topButtonBox);
        root.setStyle("-fx-background-color: #f4f4f4;");
        
        show(scene, s);
	}

	private void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void show(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
