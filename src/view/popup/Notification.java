package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
import controller.popup.NotificationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ActivityLog;
import model.MGWindow;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class Notification {	
	public static StackPane showErrorMessage(String message) {
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label errorMessage = new Label("Notification");
		Label content = new Label(message);
		NotificationController.setLabelFont(errorMessage);
		
		Button ok = new Button("Ok");
		
		NotificationController.addButtonAction(window, ok);
		NotificationController.configureBorderpane(root, errorMessage, content, ok);
		
		StackPane container = new StackPane(root);
		NotificationController.setContainer(window, container);
		return container;
	}
}
