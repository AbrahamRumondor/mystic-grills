package view.guest;

import model.ActivityLog;
import controller.WindowController;
import controller.UserController.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import model.User;
import view.MGWindow;

public class GuestLogin{
	
	private WindowController windowController = WindowController.getInstance();
	
	private static UserController userController = UserController.getInstance();
	
	public GridPane display() {
		
		GridPane gridPane = new GridPane();
		
		Label email = new Label("Email");
		Label password = new Label("Password");
		
		TextField emailTf = new TextField();
		
		PasswordField passwordPf = new PasswordField();
		passwordPf.setPromptText("Enter Password");
		
		Button submit = new Button("Submit");
		
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 	
            	// disini nanti view notification akan dipanggil.
	            Integer validityId = UserController.authenticateUser(emailTf.getText(),passwordPf.getText());
	            
	            if(validityId != null) {
	            	User user = UserController.getUserById(validityId);
	            	userController.setCurrentUser(user);
	            	
	            	windowController.goToMainMenu(user);
	            }
	            
            } 
        }; 
        
        submit.setOnAction(event); 
        
//        masukin semua ke gridpane
        gridPane.add(email, 1, 1); 
		gridPane.add(password, 1, 2);
		gridPane.add(emailTf, 2, 1); 
		gridPane.add(passwordPf, 2, 2);
		gridPane.add(submit, 1, 3);
		
//		set gridpane
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
		
		return gridPane;
	}
	
	
	
	
	
}

