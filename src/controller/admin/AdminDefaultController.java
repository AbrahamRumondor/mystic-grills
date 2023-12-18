package controller.admin;

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
import view.admin.AdminDefault;

public class AdminDefaultController {
	
	private static AdminDefault adminDefault = new AdminDefault();
	
	public static void addAction(Button addUser,Button allMenu, Button allUser, Stage s, Scene scene, BorderPane borderPane) {
		addUser.setOnAction(
				e -> {	
					AdminListController.displayAdminMenu("Menu");
				}	
		);
		
		allMenu.setOnAction(
				e -> {	
					AdminListController.displayAdminMenu("Menu");
				}	
		);
		
		allUser.setOnAction(
				e -> {
					AdminListController.displayAdminMenu("User");
				}	
		);
	}

	public static void goToAdminDefault() {
	//		remove stack, remove isi stackpane
			MGWindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();

			adminDefault.display(MGWindow.getWindow().stage);
		}
	
//	configure view
	public static void setRootStackpane(StackPane root, BorderPane borderPane) {
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        root.getChildren().addAll(borderPane);
	}

	public static void configureBorderpane(BorderPane borderPane, Label userRoleLbl, HBox headerPane, HBox centerPane) {
		borderPane.setTop(headerPane);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(userRoleLbl);
		BorderPane.setAlignment(userRoleLbl, Pos.BOTTOM_RIGHT);
	}

	public static void createCenterPane(Button addMenu, Button allMenu, Button addUser, HBox centerPane) {
		centerPane.setAlignment(Pos.CENTER);
		centerPane.getChildren().addAll(addMenu, allMenu, addUser);
		centerPane.setSpacing(100);
	}

	public static void createHeaderPane(Label userNameLbl, HBox headerPane) {
		headerPane.getChildren().addAll(userNameLbl);
		headerPane.setAlignment(Pos.CENTER);
	}

	public static void setLabelFont(Label userNameLbl, Label userRoleLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		userRoleLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
	}
	
	public static void showAdminDefault(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
}
