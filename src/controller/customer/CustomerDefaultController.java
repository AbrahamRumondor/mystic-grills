package controller.customer;

import controller.WindowController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import view.MGWindow;
import controller.customer.CustomerMenuController;

public class CustomerDefaultController {
	
	private static WindowController windowController = WindowController.getInstance();

	
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


	public static void goToCustomerDefault(User user) {
	//		remove stack, remove isi stackpane
			WindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();
			
			if(user.getUserRole().equals("Customer")) {
				WindowController.customerDefault.display(MGWindow.getWindow().stage);
			}
		}
	
}
