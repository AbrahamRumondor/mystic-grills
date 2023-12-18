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
import controller.MGWindowController;
import controller.customer.CustomerListController;
import controller.model.UserController;

public class CustomerList {
	
	public static MGWindowController windowController = MGWindowController.getInstance();	
	
	public void display(Stage s, String option) {
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		Label userNameLbl = new Label("Welcome, " + userName);
		
		HBox headerBox = new HBox();
		CustomerListController.createHeaderBox(userNameLbl, headerBox);
		CustomerListController.setBorderpane(borderPane, headerBox);
		
		Label positionLbl = new Label("Restaurant Menu ");
		Button allMenuBtn = new Button("All Menu");
		Button viewOrderBtn = new Button("My Order");
		Button logOutBtn = new Button("Log Out");
		Button home = new Button("Home");
		
		HBox topButtonBox = new HBox();
		CustomerListController.createTopButtonBox(positionLbl, allMenuBtn, viewOrderBtn, logOutBtn, topButtonBox);
		
		CustomerListController.setLabelFont(userNameLbl, positionLbl);
		CustomerListController.setStackpane(borderPane, topButtonBox, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        CustomerListController.getDisplay(option, s, borderPane, positionLbl);
		
        CustomerListController.addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        CustomerListController.setRootStackpane(root, borderPane, home, topButtonBox);
        CustomerListController.showCustomerList(scene, s);
	}
}
