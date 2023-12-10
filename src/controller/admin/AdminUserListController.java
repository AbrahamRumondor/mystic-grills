package controller.admin;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.User;
import view.admin.AdminUserList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.DeleteUser;
import view.popup.UpdateUser;

public class AdminUserListController {
	
	public static ObservableList<User> getAllData() {
		ObservableList<User> users = FXCollections.observableArrayList();
		
		ArrayList<User> arrayUsers = User.getAllUsers();
		
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
			UpdateUser.show(currentUser.getUserId(), updateBtn);
		});
		
		deleteBtn.setOnAction(e ->{ 
			deleteBtn.setDisable(true);
			DeleteUser.show(currentUser.getUserId(),deleteBtn, updateBtn);
		});
		
	}
	
	public static TableView<User> getTable(){
		return AdminUserList.table;
	}
	
}