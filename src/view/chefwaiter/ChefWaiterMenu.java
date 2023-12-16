package view.chefwaiter;

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
import controller.chefwaiter.ChefWaiterMenuController;
import controller.customer.CustomerMenuController;

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
		defineHeaderPane(userNameLbl, headerPane);
				
		String pos = new String();
		pos = getCurrentPosition(user, pos);
		
		Label position = new Label(pos);
		Button allOrder = new Button("All Order");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonPane = new HBox();
		defineTopButtonPane(position, allOrder, logOutBtn, topButtonPane);
		
		configureBorderpane(borderPane, headerPane);
		setLabelFont(userNameLbl, position);
		Button home = new Button("Home");
        setStackpane(borderPane, topButtonPane, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        ChefWaiterMenuController.getDisplay(option, s, borderPane, position);
		
//     	define semua action button          
        ChefWaiterMenuController.addAction(allOrder, home, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        setRootStackpane(root, borderPane, topButtonPane, home);
        showChefWaiterMenu(scene, s);
	}

	private void configureBorderpane(BorderPane borderPane, HBox headerPane) {
		borderPane.setTop(headerPane);
	}

	private void setRootStackpane(StackPane root, BorderPane borderPane, HBox topButtonPane, Button home) {
		root.getChildren().addAll(borderPane, home, topButtonPane);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	private void defineTopButtonPane(Label position, Button allOrder, Button logOutBtn, HBox topButtonPane) {
		topButtonPane.setMaxSize(450, 50);
		topButtonPane.setAlignment(Pos.TOP_RIGHT);
		topButtonPane.setSpacing(20);
		topButtonPane.getChildren().addAll(position, allOrder, logOutBtn);
	}

	private String getCurrentPosition(User user, String pos) {
		if(user.getUserRole().equals("Chef"))
			pos = "All Pending Order ";
		else if(user.getUserRole().equals("Waiter"))
			pos = "All Prepared Order";
		return pos;
	}

	private void defineHeaderPane(Label userNameLbl, HBox headerPane) {
		headerPane.getChildren().addAll(userNameLbl);
		headerPane.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
	}

	private void setLabelFont(Label userNameLbl, Label position) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		position.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	private void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void showChefWaiterMenu(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
