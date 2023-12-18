package controller.popup;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ActivityLog;
import model.MGWindow;

public class NotificationController {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void addButtonAction(MGWindow window, Button ok) {
		ok.setOnAction(
				e -> {
					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
					}
					
				}
		);
	}
	
//	configure view
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(250, 150);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	public static void configureBorderpane(BorderPane root, Label errorMessage, Label content, Button ok) {
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setTop(errorMessage);
		root.setCenter(content);
		root.setBottom(ok);
	}

	public static void setLabelFont(Label errorMessage) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		errorMessage.setFont(font);
	}
	
}
