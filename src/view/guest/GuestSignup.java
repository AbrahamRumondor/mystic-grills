package view.guest;

import controller.guest.GuestSignupController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
        
		GuestSignupController.addGuestSignupAction(submit, nameTf, emailTf, passwordPf, confirmPf);
        
//      masukin semua ke gridpane
        GuestSignupController.addGridpane(gridPane,username,email, nameTf, emailTf, password, confirmPassword, passwordPf, confirmPf, submit);
//		set gridpane
        GuestSignupController.setGridpane(gridPane);
		return gridPane;
	}	
}