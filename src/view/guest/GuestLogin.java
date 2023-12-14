package view.guest;

import model.ActivityLog;
import controller.MGWindowController;
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
import view.MGWindow;

public class GuestLogin{
	
	public GridPane display() {
		
		GridPane gridPane = new GridPane();
		
		Label email = new Label("Email");
		Label password = new Label("Password");
		
		TextField emailTf = new TextField();
		
		PasswordField passwordPf = new PasswordField();
		passwordPf.setPromptText("Enter Password");
		
		Button submit = new Button("Submit");
		GuestController.addGuestLoginAction(submit, emailTf,passwordPf);
        
//      masukin semua ke gridpane
        addGridpane(gridPane, email, password, emailTf, passwordPf, submit);
		
//		set gridpane
		setGridpane(gridPane);
		
		return gridPane;
	}
	
	private void addGridpane(GridPane gridPane, Label email, Label password, TextField emailTf, PasswordField passwordPf, Button submit) {
		gridPane.add(email, 1, 0); 
		gridPane.add(password, 1, 1);
		gridPane.add(emailTf, 2, 0); 
		gridPane.add(passwordPf, 2, 1);
		gridPane.add(submit, 1, 2);
	}
	
	private void setGridpane(GridPane gridPane) {
		gridPane.setHgap(50);
		gridPane.setVgap(50);
		gridPane.setMaxSize(350, 250);
	}
	
	
	
	
	
}

