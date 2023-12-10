package controller.customer;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.MenuItem;
import view.AddMenu;

public class CustomerMenuListController {
	
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
	
	public static void addAction(String menuName, Button addBtn, MenuItem currentItem, TableView<MenuItem> table){
		if(menuName == null) addBtn.setDisable(true);
		else addBtn.setDisable(false);
		
		addBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			AddMenu.show(currentItem,addBtn, "Add");
		
			refreshTableView(table);
		});
	}
	
}
