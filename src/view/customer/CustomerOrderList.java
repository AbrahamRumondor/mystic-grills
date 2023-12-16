package view.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.OrderController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.customer.CustomerDefaultController;
import controller.customer.CustomerOrderListController;

import java.sql.Date;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import view.popup.DeleteMenuOrder;
import model.MenuItem;
import model.Order;
import model.Connect;
import model.OrderItem;


public class CustomerOrderList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, submitBtn;
	VBox formPane, namePane, passwordPane, idPane, submitPane;
	Label nameLbl, descriptionLbl, priceLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	String orderQty, orderName, menuPrice;
	
	MenuItem currentItem;
	
	private void makeTable() {
		table = new TableView<>();

		TableColumn<OrderItem, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity" ));
		TableColumn<OrderItem, String> menuNameColumn = new TableColumn<>("Menu Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
		TableColumn<OrderItem, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		ObservableList<OrderItem> items = CustomerOrderListController.getAllData();
		defineOrderItemTable(quantityColumn, menuNameColumn, priceColumn, items);

		assignTableItemToLocal();
		CustomerOrderListController.addAction(orderQty, updBtn, deleteBtn, submitBtn, addBtn, currentItem, table);
	}

	void makeForm(){
		addBtn = new Button("Add New Menu");
		updBtn = new Button("Update Order Menu");
		deleteBtn = new Button("Delete Order Menu");

		HBox buttonPane = new HBox();
		createButtonPane(buttonPane);
		
		formPane = new VBox(10);
		createFormBox(buttonPane);
	}

	void makeSubmitPane(){
		submitBtn = new Button("Submit Order");
		
		submitPane = new VBox();
		createSubmitPane();
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeSubmitPane();
		makeTable();

		VBox pagePane = new VBox(10);
		createPagePane(pagePane);

		createRootStackpane(pagePane);
		
		CustomerOrderListController.refreshTableView(table);
		
		return root;
	}

	private void createFormBox(HBox buttonPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	private void createButtonPane(HBox buttonPane) {
		buttonPane.getChildren().addAll(addBtn, updBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	

	private void createSubmitPane() {
		submitPane.getChildren().addAll(submitBtn);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	private void createRootStackpane(VBox page) {
		root = new StackPane();
		root.getChildren().add(page);
	}

	private void createPagePane(VBox page) {
		page.getChildren().addAll(formPane, table, submitPane);
		page.setPadding(new Insets(10));
	}
	
	private void assignTableItemToLocal() {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentItem = newValue.getMenuItem();
				orderQty = newValue.getQuantity().toString();
				orderName = newValue.getMenuItem().getMenuItemName();
				menuPrice = String.valueOf(newValue.getMenuItem().getMenuItemPrice() * newValue.getQuantity());
				
				CustomerOrderListController.addAction(orderQty, updBtn, deleteBtn, submitBtn, addBtn, currentItem, table);
			}
		});
	}
	
	private void defineOrderItemTable(TableColumn<OrderItem, String> quantityColumn,
			TableColumn<OrderItem, String> menuNameColumn, TableColumn<OrderItem, String> priceColumn,
			ObservableList<OrderItem> items) {
		table.getColumns().addAll(quantityColumn, menuNameColumn, priceColumn);
		table.setItems(items);
	}
	
}
