package view.guest;

import model.ActivityLog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.User;

public class GuestSignup{
	
	
	
	public static GridPane display() {
		
		GridPane gridPane = new GridPane();
		
		Label username = new Label("Username");
		Label email = new Label("Email");
		Label password = new Label("Password");
		Label confirmPassword = new Label("Confirm Password");
		
		TextField nameTf = new TextField();
		
		TextField emailTf = new TextField();
		
		PasswordField passwordPf = new PasswordField();

		PasswordField confirmPf = new PasswordField();

		Button submit = new Button("Submit");

		Label t1 = new Label();
		
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 	
//            	User newUser = new User(nameTf.getText(), passwordPf.getText());
//            	newUser.insert();
            	
            	
            	
            } 
        }; 
        
        submit.setOnAction(event); 
        
        gridPane.add(username, 1, 1); 
		gridPane.add(email, 1, 2);
		gridPane.add(nameTf, 2, 1); 
		gridPane.add(emailTf, 2, 2);
		
		gridPane.add(password, 1, 3); 
		gridPane.add(confirmPassword, 1, 4);
		gridPane.add(passwordPf, 2, 3);
		gridPane.add(confirmPf, 2, 4);
		
		gridPane.add(submit, 1, 5);
		gridPane.add(t1, 1, 6); 
		
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
		
		
		
		
		return gridPane;
	}
	
	
	
	
	
}

