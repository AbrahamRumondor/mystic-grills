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
import view.MGWindow;
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
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		
//		set header
		HBox header = new HBox();
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
		
		borderPane.setTop(header);
		
		Label position = new Label();
		position.setFont(Font.font(null, FontWeight.BOLD, 20));
		Button allMenuBtn = new Button("View Menu");
		Button viewOrderBtn = new Button("View User");
		Button logOutBtn = new Button("Log Out");
		
		HBox topButtonBox = new HBox();
		topButtonBox.setMaxSize(450, 50);
		topButtonBox.setAlignment(Pos.TOP_RIGHT);
		topButtonBox.setSpacing(20);
		topButtonBox.getChildren().addAll(position, allMenuBtn, viewOrderBtn, logOutBtn);
		
//		set button back ke stackpane
		Button home = new Button("Home");
		
        setStackpane(borderPane, topButtonBox, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        AdminListController.getDisplay(option, s, borderPane, position);
		
//     	define semua action button          
        AdminListController.addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane, logOutBtn);
        
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
