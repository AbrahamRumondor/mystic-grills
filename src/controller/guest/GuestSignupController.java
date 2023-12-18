package controller.guest;

import controller.model.UserController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.guest.GuestSignup;

public class GuestSignupController {

	private static GuestSignup guestSignup = new GuestSignup();

	public static Node displayGuestSignup() {
		return guestSignup.display();
	}

	public static void addGuestSignupAction(Button submit, TextField nameTf, TextField emailTf, PasswordField passwordPf, PasswordField confirmPf) {
	    submit.setOnAction(
			e -> {
				// disini nanti view notification akan dipanggil.
	            UserController.createUser("Customer", nameTf.getText(), emailTf.getText(), passwordPf.getText(), confirmPf.getText());
			}	
		);
	}
	

//	configure view
	public static void addGridpane(GridPane gridPane, Label username, Label email, TextField nameTf, TextField emailTf, Label password, Label confirmPassword, PasswordField passwordPf, PasswordField confirmPf, Button submit) {
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
	
	public static void setGridpane(GridPane gridPane) {
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
	}
	
	
}
