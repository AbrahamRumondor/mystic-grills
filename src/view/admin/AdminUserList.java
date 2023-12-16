package view.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.customer.CustomerMenuListController;

import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import view.popup.AddMenuOrder;
import model.MenuItem;
import model.Connect;


public class AdminUserList {
	public static StackPane root;

	public static TableView<User> table;
	Button updateBtn, deleteBtn;
	VBox formPane, namePane, passwordPane, idPane;
	Label nameLbl, emailLbl, roleLbl;
	TextField nameTxt, emailTxt, roleTxt;
	
	String userName, userEmail, userRole;
	
	User currentUser;
	
	private void makeTable() {
		table = new TableView<>();

		table = new TableView<>();
		TableColumn<User, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName" ));
		TableColumn<User, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail" ));
		TableColumn<User, String> roleColumn = new TableColumn<>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		
		table.getColumns().addAll(nameColumn, emailColumn, roleColumn);

		ObservableList<User> items = AdminUserListController.getAllData();
		AdminUserListController.defineUserToTable(items, table);

		assignTableItemToLocal();
		AdminUserListController.addAction(updateBtn, currentUser, table, deleteBtn);
	}

	void makeForm(){
		updateBtn = new Button("Update User's Role");
		deleteBtn = new Button("Delete User");

		HBox buttonPane = new HBox();
		AdminUserListController.defineButtonPane(buttonPane, updateBtn, deleteBtn);
		formPane = new VBox(10);
		AdminUserListController.defineFormPane(buttonPane, formPane);
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeTable();

		VBox pagePane = new VBox(10);
		AdminUserListController.definePagePane(pagePane, formPane, table);

		root = AdminUserListController.createRootStackpane(pagePane, root);
		return root;
	}
	
	private void assignTableItemToLocal() {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentUser = newValue;
				System.out.println(newValue.getUserName() + " is " + newValue.getUserRole() +" months old");
				userName = newValue.getUserName();
				userEmail = newValue.getUserEmail();
				userRole = newValue.getUserRole();
				
				AdminUserListController.addAction(updateBtn, currentUser,table, deleteBtn);
			}
		});
	}
}
