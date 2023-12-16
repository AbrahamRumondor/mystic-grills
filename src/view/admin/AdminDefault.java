package view.admin;

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
import controller.admin.AdminDefaultController;
import controller.model.UserController;

public class AdminDefault {
	
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
		AdminDefaultController.setLabelFont(userNameLbl, userRoleLbl);
		
		HBox headerPane = new HBox();
		AdminDefaultController.createHeaderPane(userNameLbl, headerPane);
		
//		 untuk login dan signup
		Button addMenu = new Button("Add Menu Item");
		Button allMenu = new Button("View Menu");
		Button addUser = new Button("View User");
		HBox centerPane = new HBox();
		AdminDefaultController.createCenterPane(addMenu, allMenu, addUser, centerPane);
		
		AdminDefaultController.configureBorderpane(borderPane, userRoleLbl, headerPane, centerPane);
		activityLog.add(centerPane);
		
//     define semua action button          
        AdminDefaultController.addAction(addMenu, allMenu, addUser, s, scene, borderPane);
        
//      masukin semuanya ke stackpane
        AdminDefaultController.setRootStackpane(root, borderPane);

        AdminDefaultController.showAdminDefault(scene, s);
	}
}
