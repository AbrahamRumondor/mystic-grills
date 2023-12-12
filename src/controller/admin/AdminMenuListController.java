package controller.admin;

import java.util.ArrayList;

import controller.popup.DeletePopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.User;
import view.admin.AdminMenuList;
import view.admin.AdminUserList;
import view.popup.AddMenuOrder;
import view.popup.DeletePopup;
import view.popup.menuitem.MenuItemPopup;

public class AdminMenuListController {
	
	public static ObservableList<MenuItem> getAllData() {
		ObservableList<MenuItem> items = FXCollections.observableArrayList();
		
		ArrayList<MenuItem> arrayItems = MenuItem.getAllMenuItems();
		
		for (MenuItem menuItem : arrayItems) {
			items.add(menuItem);
		}
		
		return items;

	}
	
	public static void refreshTableView(TableView<MenuItem> table) {
		ObservableList<MenuItem> items = FXCollections.observableArrayList();
		items = getAllData();
		table.setItems(items);
	}
	
	public static TableView<MenuItem> getTable(){
		return AdminMenuList.table;
	}
	
	public static void addAction(MenuItem chosenMenu, Button addBtn, Button updateBtn, Button deleteBtn, MenuItem currentItem, TableView<MenuItem> table){
		if(chosenMenu == null) {
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
		} else {
			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
		addBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
			MenuItemPopup.show(addBtn, null, null, null, "Add New");
		});
		
		updateBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
			MenuItemPopup.show(addBtn, updateBtn, deleteBtn, chosenMenu, "Update");
		});
		
		deleteBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
			DeletePopup.show(chosenMenu.getMenuItemId(), deleteBtn, updateBtn, "Menu Item", addBtn);
		});
		
	}
	
}
