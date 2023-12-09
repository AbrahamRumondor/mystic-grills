package view.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.OrderController;
import controller.WindowController;
import controller.UserController.UserController;

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
import view.AddMenu;
import view.DeleteMenu;
import model.MenuItem;
import model.Order;
import model.Connect;
import model.OrderItem;


public class CustomerOrderList {
	public static StackPane root;
	
	private static WindowController windowController = WindowController.getInstance();
	
	static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, submitBtn;
	VBox form, namePane, passwordPane, idPane, submitPane;
	Label nameLbl, descriptionLbl, priceLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	String orderQty, orderName, menuPrice;
	
	MenuItem currentItem;
	
	private void makeTable() {
		table = new TableView<>();

		table = new TableView<>();
		TableColumn<OrderItem, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity" ));
		TableColumn<OrderItem, String> menuNameColumn = new TableColumn<>("Menu Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
		TableColumn<OrderItem, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		table.getColumns().addAll(quantityColumn, menuNameColumn, priceColumn);

		ObservableList<OrderItem> items = getAllData();
		table.setItems(items);

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentItem = newValue.getMenuItem();
				System.out.println(newValue.getMenuItem().getMenuItemName() + " is " + newValue.getQuantity() +" months old");
				orderQty = newValue.getQuantity().toString();
				orderName = newValue.getMenuItem().getMenuItemName();
				menuPrice = String.valueOf(newValue.getMenuItem().getMenuItemPrice() * newValue.getQuantity());
				
				addAction();
			}
		});
		
		
		addAction();
	}

	void makeForm(){
		addBtn = new Button("Add New Menu");
		updBtn = new Button("Update Order Menu");
		deleteBtn = new Button("Delete Order Menu");

		HBox buttonPane = new HBox();
		buttonPane.getChildren().addAll(addBtn, updBtn, deleteBtn);
		buttonPane.setSpacing(5);
		form = new VBox(10);
		form.getChildren().addAll(buttonPane);
	}
	
	void setSubmitBtn(){
		submitBtn = new Button("Submit Order");
		
		submitPane = new VBox();
		submitPane.getChildren().addAll(submitBtn);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	void addAction(){
		if(orderQty == null) {
			updBtn.setDisable(true);
			deleteBtn.setDisable(true);
		}
		else {
			updBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
		if(Order.getOrderItems().size() == 0) submitBtn.setDisable(true);
		
		updBtn.setOnAction(e ->{ 
			updBtn.setDisable(true);
			AddMenu.show(currentItem,updBtn, "Update");
			refreshTableView();
		});
		
		deleteBtn.setOnAction(e ->{ 
			deleteBtn.setDisable(true);
			DeleteMenu.show(currentItem,deleteBtn);
		});
		
		addBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			windowController.displayCustomerMenu("Menu");
		});
		
		submitBtn.setOnAction(e ->{ 
			submitBtn.setDisable(true);
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date orderDate = Date.valueOf(currentDateTime.toLocalDate());
			
			OrderController.createOrder(Order.getOrderUser(), Order.getOrderItems(), orderDate);
			
//			reset order trus back to main menu
			User user = UserController.getCurrentUser();
			OrderController.setOngoingOrder(user);
			WindowController.goToMainMenu(user);
			
		});
		
		
	}

	public static void refreshTableView() {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		items = getAllData();
		table.setItems(items);
	}

	private static ObservableList<OrderItem> getAllData() {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		
		ArrayList<OrderItem> arrayItems = Order.getOrderItems();
		
		for (OrderItem item : arrayItems) {
			items.add(item);
		}
		
		return items;
	}
	
	public StackPane display(Stage s) {
		makeForm();
		setSubmitBtn();
		makeTable();

		VBox page = new VBox(10);
		page.getChildren().addAll(form, table, submitPane);
		page.setPadding(new Insets(10));

		root = new StackPane();
//		Scene scene = new Scene(root, 400, 400);
		root.getChildren().add(page);
		
		refreshTableView();
		
		return root;
	}


}
