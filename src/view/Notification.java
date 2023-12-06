package view;

import javafx.scene.layout.BorderPane;
import controller.WindowController;
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
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class Notification {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static StackPane showErrorMessage(String message) {
		
		MGWindow window = WindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label errorMessage = new Label("Error Message");
		Label content = new Label(message);
		Font font = Font.font(null, FontWeight.BOLD, 20);
		errorMessage.setFont(font);
		
		Button ok = new Button("Ok");
		
		ok.setOnAction(
				e -> {
					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
        				
					}
					
				}	
		);
		
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setTop(errorMessage);
		root.setCenter(content);
		root.setBottom(ok);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(250, 150);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
		return container;
		
	}
	
}
