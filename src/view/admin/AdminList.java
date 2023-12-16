package view.admin;

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
import controller.UserController.*;
import controller.admin.AdminListController;
import controller.customer.CustomerMenuController;

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
		createHeaderPane(userNameLbl, headerPane);
		
		Label position = new Label();
		Button allMenuBtn = new Button("View Menu");
		Button viewOrderBtn = new Button("View User");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonPane = new HBox();
		createTopButtonPane(position, allMenuBtn, viewOrderBtn, logOutBtn, topButtonPane);
		
//		set button back ke stackpane
		Button home = new Button("Home");
		
		setLabelFont(userNameLbl, position);
		configureBorderpane(borderPane, headerPane);
        setStackpane(borderPane, topButtonPane, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        AdminListController.getDisplay(option, s, borderPane, position);
        
//     	define semua action button          
        AdminListController.addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane, logOutBtn);
        
//      masukin dan setting semuanya ke stackpane
        setRootStackpane(root, borderPane, topButtonPane, home);
        showAdminList(scene, s);
	}

	private void setRootStackpane(StackPane root, BorderPane borderPane, HBox topButtonPane, Button home) {
		root.getChildren().addAll(borderPane, home, topButtonPane);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	private void configureBorderpane(BorderPane borderPane, HBox headerPane) {
		borderPane.setTop(headerPane);
	}

	private void createTopButtonPane(Label position, Button allMenuBtn, Button viewOrderBtn, Button logOutBtn,
			HBox topButtonPane) {
		topButtonPane.setMaxSize(450, 50);
		topButtonPane.setAlignment(Pos.TOP_RIGHT);
		topButtonPane.setSpacing(20);
		topButtonPane.getChildren().addAll(position, allMenuBtn, viewOrderBtn, logOutBtn);
	}

	private void createHeaderPane(Label userNameLbl, HBox headerPane) {
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
	
	public static void showAdminList(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
