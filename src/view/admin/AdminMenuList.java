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
		defineMenuItemTable(items);

		assignTableItemToLocal();
		AdminMenuListController.addAction(currentItem, addBtn, updateBtn, deleteBtn, currentItem, table);
	}

	void makeForm(){
		addBtn = new Button("Add New Menu");
		updateBtn = new Button("Update Menu");
		deleteBtn = new Button("Delete Menu");

		HBox buttonPane = new HBox();
		createButtonPane(buttonPane);
		formPane = new VBox(10);
		createFormPane(buttonPane);
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeTable();

		VBox pagePane = new VBox(10);
		createPagePane(pagePane);

		createRootStackpane(pagePane);
		return root;
	}

	private void defineMenuItemTable(ObservableList<MenuItem> items) {
		table.setItems(items);
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
	
	private void createRootStackpane(VBox pagePane) {
		root = new StackPane();
		root.getChildren().add(pagePane);
	}

	private void createPagePane(VBox pagePane) {
		pagePane.getChildren().addAll(formPane, table);
		pagePane.setPadding(new Insets(10));
	}
	
	private void createFormPane(HBox buttonPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	private void createButtonPane(HBox buttonPane) {
		buttonPane.getChildren().addAll(addBtn, updateBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	

}
