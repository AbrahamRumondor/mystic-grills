package controller.customer;

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
import view.popup.AddMenuOrder;

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
			AddMenuOrder.show(currentItem,addBtn, "Add");
		
			refreshTableView(table);
		});
	}
	
//	configure view
	public static void createFormBox(HBox buttonPane, VBox formPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	public static void createButtonPane(HBox buttonPane, Button addBtn) {
		buttonPane.getChildren().addAll(addBtn);
		buttonPane.setSpacing(5);
	}
	
	public static void createPagePane(VBox page, VBox formPane, TableView<MenuItem> table) {
		page.getChildren().addAll(formPane, table);
		page.setPadding(new Insets(10));
	}

	public static StackPane createRootStackpane(VBox page, StackPane root) {
		root = new StackPane();
		root.getChildren().add(page);
		return root;
	}
}