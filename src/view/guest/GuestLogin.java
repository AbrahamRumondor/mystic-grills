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

public class GuestLogin{
	
	
	
	public static GridPane display() {
		
		GridPane gridPane = new GridPane();
		
		Label username = new Label("Username");
		Label password = new Label("Password");
		
		TextField nameTf = new TextField();
		
		PasswordField passwordPf = new PasswordField();
		passwordPf.setPromptText("Enter Password");
		
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
        
//        masukin semua ke gridpane
        gridPane.add(username, 1, 1); 
		gridPane.add(password, 1, 2);
		gridPane.add(nameTf, 2, 1); 
		gridPane.add(passwordPf, 2, 2);
		gridPane.add(submit, 1, 3);
		gridPane.add(t1, 1, 4); 
		
//		set gridpane
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
		
		return gridPane;
	}
	
	
	
	
	
}

