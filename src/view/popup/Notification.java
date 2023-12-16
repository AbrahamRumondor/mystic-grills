package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
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
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static StackPane showErrorMessage(String message) {
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label errorMessage = new Label("Notification");
		Label content = new Label(message);
		setLabelFont(errorMessage);
		
		Button ok = new Button("Ok");
		
		addButtonAction(window, ok);
		configureBorderpane(root, errorMessage, content, ok);
		
		StackPane container = new StackPane(root);
		setContainer(window, container);
		return container;
		
	}

	private static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(250, 150);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	private static void configureBorderpane(BorderPane root, Label errorMessage, Label content, Button ok) {
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setTop(errorMessage);
		root.setCenter(content);
		root.setBottom(ok);
	}

	private static void addButtonAction(MGWindow window, Button ok) {
		ok.setOnAction(
				e -> {
					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
					}
					
				}
		);
	}

	private static void setLabelFont(Label errorMessage) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		errorMessage.setFont(font);
	}
	
}
