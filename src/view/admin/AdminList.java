package view.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.MGWindow;
import controller.MGWindowController;
import controller.admin.AdminListController;
import controller.model.UserController;

public class AdminList {
	
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
		AdminListController.createHeaderPane(userNameLbl, headerPane);
		
		Label position = new Label();
		Button allMenuBtn = new Button("View Menu");
		Button viewOrderBtn = new Button("View User");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonPane = new HBox();
		AdminListController.createTopButtonPane(position, allMenuBtn, viewOrderBtn, logOutBtn, topButtonPane);
		
//		set button back ke stackpane
		Button home = new Button("Home");
		
		AdminListController.setLabelFont(userNameLbl, position);
		AdminListController.configureBorderpane(borderPane, headerPane);
		AdminListController.setStackpane(borderPane, topButtonPane, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        AdminListController.getDisplay(option, s, borderPane, position);
        
//     	define semua action button          
        AdminListController.addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane, logOutBtn);
        
//      masukin dan setting semuanya ke stackpane
        AdminListController.setRootStackpane(root, borderPane, topButtonPane, home);
        AdminListController.showAdminList(scene, s);
	}
}
