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
import controller.UserController.*;
import controller.customer.CustomerMenuController;

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
		createHeaderBox(userNameLbl, headerBox);
		setBorderpane(borderPane, headerBox);
		
		Label positionLbl = new Label("Restaurant Menu ");
		Button allMenuBtn = new Button("All Menu");
		Button viewOrderBtn = new Button("My Order");
		Button logOutBtn = new Button("Log Out");
		Button home = new Button("Home");
		
		HBox topButtonBox = new HBox();
		createTopButtonBox(positionLbl, allMenuBtn, viewOrderBtn, logOutBtn, topButtonBox);
		
		setLabelFont(userNameLbl, positionLbl);
        setStackpane(borderPane, topButtonBox, home);
        
//      Disini Customer Menu bisa tampilin 2 jenis display, itu ditentukan dari function ini.
        CustomerMenuController.getDisplay(option, s, borderPane, positionLbl);
		
        CustomerMenuController.addAction(allMenuBtn, home, viewOrderBtn, s, scene, borderPane, logOutBtn);
        
//      masukin semuanya ke stackpane
        setRootStackpane(root, borderPane, home, topButtonBox);
        showCustomerList(scene, s);
	}

	private void setBorderpane(BorderPane borderPane, HBox headerBox) {
		borderPane.setTop(headerBox);
	}

	private void setRootStackpane(StackPane root, BorderPane borderPane, Button home, HBox topButtonBox) {
		root.getChildren().addAll(borderPane, home, topButtonBox);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	private void createHeaderBox(Label userNameLbl, HBox headerBox) {
		headerBox.getChildren().addAll(userNameLbl);
		headerBox.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
	}

	private void createTopButtonBox(Label positionLbl, Button allMenuBtn, Button viewOrderBtn, Button logOutBtn,
			HBox topButtonBox) {
		topButtonBox.setMaxSize(450, 50);
		topButtonBox.setAlignment(Pos.TOP_RIGHT);
		topButtonBox.setSpacing(20);
		topButtonBox.getChildren().addAll(positionLbl, allMenuBtn, viewOrderBtn, logOutBtn);
	}

	private void setLabelFont(Label userNameLbl, Label positionLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		positionLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	private void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void showCustomerList(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
