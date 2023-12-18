package controller.admin;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MenuItem;
import view.admin.AdminMenuList;
import view.popup.DeletePopup;
import view.popup.MenuItemPopup;

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
	
//	configure view
	public static StackPane createRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void createPagePane(VBox pagePane, VBox formPane, TableView<MenuItem> table) {
		pagePane.getChildren().addAll(formPane, table);
		pagePane.setPadding(new Insets(10));
	}
	
	public static void createFormPane(HBox buttonPane, VBox formPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	public static void createButtonPane(HBox buttonPane, Button addBtn, Button updateBtn, Button deleteBtn) {
		buttonPane.getChildren().addAll(addBtn, updateBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	
	public static void defineMenuItemTable(ObservableList<MenuItem> items, TableView<MenuItem> table) {
		table.setItems(items);
	}
	
}
