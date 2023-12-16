package view.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.admin.AdminMenuListController;
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


public class AdminMenuList {
	public static StackPane root;

	public static TableView<MenuItem> table;
	Button addBtn, updateBtn, deleteBtn;
	VBox formPane, namePane, passwordPane, idPane;
	Label nameLbl, descriptionLbl, priceLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	String menuName, menuDescription, menuPrice;
	
	MenuItem currentItem;
	
	private void makeTable() {
		table = new TableView<>();

		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
		TableColumn<MenuItem, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		
		table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn);

		ObservableList<MenuItem> items = CustomerMenuListController.getAllData();
		AdminMenuListController.defineMenuItemTable(items, table);

		assignTableItemToLocal();
		AdminMenuListController.addAction(currentItem, addBtn, updateBtn, deleteBtn, currentItem, table);
	}

	void makeForm(){
		addBtn = new Button("Add New Menu");
		updateBtn = new Button("Update Menu");
		deleteBtn = new Button("Delete Menu");

		HBox buttonPane = new HBox();
		AdminMenuListController.createButtonPane(buttonPane, addBtn, updateBtn, deleteBtn);
		formPane = new VBox(10);
		AdminMenuListController.createFormPane(buttonPane, formPane);
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeTable();

		VBox pagePane = new VBox(10);
		AdminMenuListController.createPagePane(pagePane, formPane, table);

		root = AdminMenuListController.createRootStackpane(pagePane, root);
		return root;
	}

	private void assignTableItemToLocal() {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentItem = newValue;
				System.out.println(newValue.getMenuItemName() + " is " + newValue.getMenuItemId() +" months old");
				menuName = newValue.getMenuItemName();
				menuDescription = newValue.getMenuItemDescription();
				menuPrice = newValue.getMenuItemPrice().toString();
				
				AdminMenuListController.addAction(currentItem, addBtn, updateBtn, deleteBtn, currentItem,table);
			}
		});
	}
}
