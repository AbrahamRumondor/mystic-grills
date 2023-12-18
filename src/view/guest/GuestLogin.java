package view.guest;

import controller.guest.GuestLoginController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GuestLogin{
	
	public GridPane display() {
		
		GridPane gridPane = new GridPane();
		
		Label email = new Label("Email");
		Label password = new Label("Password");
		
		TextField emailTf = new TextField();
		
		PasswordField passwordPf = new PasswordField();
		passwordPf.setPromptText("Enter Password");
		
		Button submit = new Button("Submit");
		GuestLoginController.addGuestLoginAction(submit, emailTf,passwordPf);
        
//      masukin semua ke gridpane
        GuestLoginController.addGridpane(gridPane, email, password, emailTf, passwordPf, submit);
		
//		set gridpane
        GuestLoginController.setGridpane(gridPane);
		return gridPane;
	}
}

