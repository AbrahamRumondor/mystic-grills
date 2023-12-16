package view.chefwaiter;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.MGWindow;
import model.User;
import controller.MGWindowController;
import controller.chefwaiter.ChefWaiterMenuController;
import controller.model.UserController;

public class ChefWaiterMenu {
	
	public static MGWindowController windowController = MGWindowController.getInstance();	
	
	public void display(Stage s, String option) {
		
		User user = UserController.getCurrentUser();
		
		MGWindow window = MGWindowController.setWindow(s);
		StackPane root = window.root;
		Scene scene = window.scene;
		BorderPane borderPane = new BorderPane();
		
		String userName = UserController.getCurrentUser().getUserName();
		Label userNameLbl = new Label("Welcome, " + userName);
		
//		set header
		HBox headerPane = new HBox();
		ChefWaiterMenuController.defineHeaderPane(userNameLbl, headerPane);
				
		String pos = new String();
		pos = ChefWaiterMenuController.getCurrentPosition(user, pos);
		
		Label position = new Label(pos);
		Button allOrder = new Button("All Order");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonPane = new HBox();
		ChefWaiterMenuController.defineTopButtonPane(position, allOrder, logOutBtn, topButtonPane);
		
		ChefWaiterMenuController.configureBorderpane(borderPane, headerPane);
		ChefWaiterMenuController.setLabelFont(userNameLbl, position);
		Button home = new Button("Home");
		ChefWaiterMenuController.setStackpane(borderPane, topButtonPane, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        ChefWaiterMenuController.getDisplay(option, s, borderPane, position);
		
//     	define semua action button          
        ChefWaiterMenuController.addAction(allOrder, home, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        ChefWaiterMenuController.setRootStackpane(root, borderPane, topButtonPane, home);
        ChefWaiterMenuController.showChefWaiterMenu(scene, s);
	}
}
