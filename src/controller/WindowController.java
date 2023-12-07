package controller;

import controller.UserController.UserController;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.ActivityLog;
import model.User;
import view.MGWindow;
import view.customer.CustomerMenu;
import view.customer.CustomerDefault;

public class WindowController {
	
//	controller ini dipakai untuk menjaga integrity MVC dimana setiap pemanggilan View dan Model hanya bisa terjadi melalui controller.
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static WindowController windowController;
	
	private static CustomerDefault customerDefault = new CustomerDefault();
	private static CustomerMenu customerMenu = new CustomerMenu();
	
	public static WindowController getInstance() {
		if(windowController == null) {
			synchronized (WindowController.class) {
				if(windowController==null)windowController = new WindowController();
			}
		}
		return windowController;
	}
	
	public static MGWindow setWindow(Stage s) {
		return MGWindow.setWindow(s);
	}
	
	public static MGWindow getWindow() {
		return MGWindow.getWindow();
	}
	
	public static void goToMainMenu(User user) {
//		remove stack, remove isi stackpane
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		
		if(user.getUserRole().equals("Customer")) {
			customerDefault.display(MGWindow.getWindow().stage);
		}
	}
	
//	activityLog.add(userController.displayGuestSignup());
//	
//	public Node displayGuestLogin() {
//		return guestLogin.display();
//	}
	
	public void displayCustomerMenu() {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		customerMenu.display(WindowController.getWindow().stage);
	}
	
}
