package view.guest;

import model.ActivityLog;
import model.MGWindow;
import controller.UserController.UserController;
import controller.guest.GuestController;
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

public class GuestSignup{
	

	public GridPane display() {
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
        
		GuestController.addGuestSignupAction(submit, nameTf, emailTf, passwordPf, confirmPf);
        
//      masukin semua ke gridpane
        addGridpane(gridPane,username,email, nameTf, emailTf, password, confirmPassword, passwordPf, confirmPf, submit);
//		set gridpane
        setGridpane(gridPane);
		
		return gridPane;
	}
	
	private void addGridpane(GridPane gridPane, Label username, Label email, TextField nameTf, TextField emailTf, Label password, Label confirmPassword, PasswordField passwordPf, PasswordField confirmPf, Button submit) {
		gridPane.add(username, 1, 0); 
		gridPane.add(email, 1, 1);
		gridPane.add(nameTf, 2, 0); 
		gridPane.add(emailTf, 2, 1);
		
		gridPane.add(password, 1, 2); 
		gridPane.add(confirmPassword, 1, 3);
		gridPane.add(passwordPf, 2, 2);
		gridPane.add(confirmPf, 2, 3);
		
		gridPane.add(submit, 1, 4);
	}
	
	private void setGridpane(GridPane gridPane) {
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
	}
	
}