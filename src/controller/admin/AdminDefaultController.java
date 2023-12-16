package controller.admin;

import controller.MGWindowController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MGWindow;
import model.User;
import controller.customer.CustomerMenuController;
import view.admin.AdminDefault;
import view.popup.menuitem.MenuItemPopup;

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
	
}
