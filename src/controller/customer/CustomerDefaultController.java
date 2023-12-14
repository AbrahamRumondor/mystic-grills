package controller.customer;

import controller.MGWindowController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import view.MGWindow;
import controller.customer.CustomerMenuController;

public class CustomerDefaultController {
	
	private static MGWindowController windowController = MGWindowController.getInstance();

	
	public static void addAction(Button allMenu, Button orderedMenu, Stage s, Scene scene, BorderPane borderPane) {
		allMenu.setOnAction(
				e -> {	
					CustomerMenuController.displayCustomerMenu("Menu");
				}	
		);
		
		orderedMenu.setOnAction(
				e -> {
					CustomerMenuController.displayCustomerMenu("Order");
				}	
		);
	}


	public static void goToCustomerDefault() {
	//		remove stack, remove isi stackpane
			MGWindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();
			
			MGWindowController.customerDefault.display(MGWindow.getWindow().stage);
		}
	
}
