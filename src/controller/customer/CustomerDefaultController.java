package controller.customer;

import controller.MGWindowController;
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
import javafx.stage.Stage;
import model.MGWindow;
import model.User;
import controller.customer.CustomerListController;

public class CustomerDefaultController {
	
	public static void addAction(Button allMenu, Button orderedMenu, Stage s, Scene scene, BorderPane borderPane) {
		allMenu.setOnAction(
				e -> {	
					CustomerListController.displayCustomerMenu("Menu");
				}	
		);
		
		orderedMenu.setOnAction(
				e -> {
					CustomerListController.displayCustomerMenu("Order");
				}	
		);
	}


	public static void goToCustomerDefault() {
	//		remove stack, remove isi stackpane
			MGWindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();
			
			MGWindowController.customerDefault.display(MGWindow.getWindow().stage);
		}
	
//	configure view
	public static void setRootStackpane(StackPane root, BorderPane borderPane) {
		root.getChildren().addAll(borderPane);
	}

	public static void setBorderpane(BorderPane borderPane, Label userRoleLbl, HBox tengah) {
		borderPane.setBottom(userRoleLbl);
		borderPane.setCenter(tengah);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
	}

	public static void setLabelFont(Label userNameLbl, Label userRoleLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
	}

	public static void createHeaderBox(BorderPane borderPane, Label userNameLbl, HBox header) {
		header.getChildren().addAll(userNameLbl);
		header.setAlignment(Pos.CENTER);
		borderPane.setTop(header);
	}

	public static void createCenterBox(Button allMenu, Button orderedMenu, HBox tengah) {
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(allMenu, orderedMenu);
		tengah.setSpacing(100);
	}
	
	public static void showCustomerDefault(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
	
}
