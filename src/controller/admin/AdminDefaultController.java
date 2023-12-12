package controller.admin;

import controller.WindowController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import view.MGWindow;
import controller.customer.CustomerMenuController;
import view.admin.AdminDefault;
import view.popup.menuitem.MenuItemPopup;

public class AdminDefaultController {
	
	private static AdminDefault adminDefault = new AdminDefault();
	
	public static void addAction(Button allMenu, Button allUser, Button addUser, Stage s, Scene scene, BorderPane borderPane) {
		allMenu.setOnAction(
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
			WindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();

			adminDefault.display(MGWindow.getWindow().stage);
		}
	
}
