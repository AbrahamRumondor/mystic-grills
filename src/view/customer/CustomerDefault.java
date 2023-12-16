package view.customer;

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
import controller.customer.CustomerDefaultController;
import controller.model.UserController;

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
		CustomerDefaultController.setLabelFont(userNameLbl, userRoleLbl);
				
		HBox headerBox = new HBox();
		CustomerDefaultController.createHeaderBox(borderPane, userNameLbl, headerBox);
		
//		untuk login dan signup
		Button allMenu = new Button("All Menu");
		Button orderedMenu = new Button("Ordered Menu");
		
		HBox centerBox = new HBox();
		CustomerDefaultController.createCenterBox(allMenu, orderedMenu, centerBox);
		activityLog.add(centerBox);
		
//		set isi borderpane dan posisi stackpanenya	
		CustomerDefaultController.setBorderpane(borderPane, userRoleLbl, centerBox);
			
//		define semua action button          
        CustomerDefaultController.addAction(allMenu, orderedMenu, s, scene, borderPane);
        
//      masukin semuanya ke stackpane
        CustomerDefaultController.setRootStackpane(root, borderPane);

        CustomerDefaultController.showCustomerDefault(scene, s);
	}
}
