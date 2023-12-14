package controller;

import controller.UserController.UserController;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.ActivityLog;
import view.MGWindow;
import view.customer.CustomerMenu;
import view.customer.CustomerDefault;

public class MGWindowController {
	
//	controller ini dipakai untuk menjaga integrity MVC dimana setiap pemanggilan View dan Model hanya bisa terjadi melalui controller.
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static MGWindowController windowController;
	
	public static CustomerDefault customerDefault = new CustomerDefault();
	private static CustomerMenu customerMenu = new CustomerMenu();
//	private static CustomerOrder customerOrder = new CustomerOrder();
	
	public static MGWindowController getInstance() {
		if(windowController == null) {
			synchronized (MGWindowController.class) {
				if(windowController==null)windowController = new MGWindowController();
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
	

}
