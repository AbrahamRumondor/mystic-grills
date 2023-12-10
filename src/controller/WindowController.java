package controller;

import controller.UserController.UserController;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.ActivityLog;
import view.MGWindow;
import view.customer.CustomerMenu;
import view.customer.CustomerDefault;

public class WindowController {
	
//	controller ini dipakai untuk menjaga integrity MVC dimana setiap pemanggilan View dan Model hanya bisa terjadi melalui controller.
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	private static WindowController windowController;
	
	public static CustomerDefault customerDefault = new CustomerDefault();
	private static CustomerMenu customerMenu = new CustomerMenu();
//	private static CustomerOrder customerOrder = new CustomerOrder();
	
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
	

}
