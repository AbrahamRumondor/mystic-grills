package controller.admin;

import java.util.ArrayList;

import controller.model.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MenuItem;
import model.User;
import view.admin.AdminUserList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.DeletePopup;
import view.popup.UpdateUser;

public class AdminUserListController {
	
	public static ObservableList<User> getAllData() {
		ObservableList<User> users = FXCollections.observableArrayList();
		
		ArrayList<User> arrayUsers = UserController.getAllUsers();
		
		for (User menuItem : arrayUsers) {
			users.add(menuItem);
		}
		
		return users;

	}
	
	public static void refreshTableView(TableView<User> table) {
		ObservableList<User> users = FXCollections.observableArrayList();
		users = getAllData();
		table.setItems(users);
	}
	
	public static void addAction(Button updateBtn, User currentUser, TableView<User> table, Button deleteBtn){
		if(currentUser == null) {
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
		}
		else {
			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
		updateBtn.setOnAction(e ->{ 
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
			UpdateUser.show(currentUser.getUserId(), updateBtn);
		});
		
		deleteBtn.setOnAction(e ->{ 
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
			DeletePopup.show(currentUser.getUserId(),deleteBtn, updateBtn, "User", null);
		});
		
	}
	
	public static TableView<User> getTable(){
		return AdminUserList.table;
	}
	
//	configure view
	public static void defineFormPane(HBox buttonPane, VBox formPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	public static void defineButtonPane(HBox buttonPane, Button updateBtn, Button deleteBtn) {
		buttonPane.getChildren().addAll(updateBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	
	public static void defineUserToTable(ObservableList<User> items, TableView<User> table) {
		table.setItems(items);
	}
	
	public static StackPane createRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void definePagePane(VBox page, VBox formPane, TableView<User> table) {
		page.getChildren().addAll(formPane, table);
		page.setPadding(new Insets(10));
	}
	
}
