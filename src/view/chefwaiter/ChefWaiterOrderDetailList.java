package view.chefwaiter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.OrderController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.cashier.CashierViewOrderDetailListController;
import controller.chefwaiter.ChefWaiterOrderDetailListController;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import model.MenuItem;
import model.Order;
import model.Connect;
import model.OrderItem;


public class ChefWaiterOrderDetailList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, proceedBtn;
	VBox namePane, passwordPane, idPane, proceedPane, headerPane;
	Label orderIdLbl, userNameLbl, orderDateLbl, orderStatusLbl, totalLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	HBox buttonPane;
	
	Integer orderId;
//	User orderUser;
//	Date orderDate;
//	String orderStatus;
	
	MenuItem currentMenu;
	
	private void makeTable(Order order, Stage s, BorderPane borderPane, boolean isCustomer) {
		table = new TableView<>();
		
		TableColumn<OrderItem, String> menuNameColumn = new TableColumn<>("Menu Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
		TableColumn<OrderItem, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn<OrderItem, Double> totalColumn = new TableColumn<>("Sub-Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		table.getColumns().addAll(menuNameColumn, priceColumn, quantityColumn, totalColumn);

		ObservableList<OrderItem> items = ChefWaiterOrderDetailListController.getAllData(order);
		table.setItems(items);
		
		OrderController.setOrder(order);
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentMenu = newValue.getMenuItem();
				orderId = newValue.getOrderId();
				
				if(!isCustomer)
					ChefWaiterOrderDetailListController.addAction(
						proceedBtn,
						addBtn,
						updBtn,
						deleteBtn,
						order,
						table,
						currentMenu,
						s,
						borderPane);
				else
					ChefWaiterOrderDetailListController.addCustomerAction(
							proceedBtn,
							s,
							borderPane);

			}
		});
		
		if(!isCustomer)
			ChefWaiterOrderDetailListController.addAction(
				proceedBtn,
				addBtn,
				updBtn,
				deleteBtn,
				order,
				table,
				currentMenu,
				s,
				borderPane);
		else
			ChefWaiterOrderDetailListController.addCustomerAction(
					proceedBtn,
					s,
					borderPane);
	}
	
	void headerPane(Order order) {
		 orderIdLbl = new Label("Order Id: " + order.getOrderId());
		 userNameLbl = new Label("Order Client: " + order.getOrderUser().getUserName());
		 orderDateLbl =  new Label("Order Date: " + order.getOrderDateString());
		 orderStatusLbl = new Label("Order Status: " + order.getOrderStatus());
		 
		 orderIdLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 orderDateLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 orderStatusLbl.setFont(Font.font(null, FontWeight.BOLD, 14));

		 
		 headerPane = new VBox();
		 headerPane.getChildren().addAll(
				 orderIdLbl, 
				 userNameLbl, 
				 orderDateLbl,
				 orderStatusLbl);
		 headerPane.setPadding(new Insets(10));
	}
	
	void makeForm(Order order){
		addBtn = new Button("Add New Menu");
		updBtn = new Button("Update Order Menu");
		deleteBtn = new Button("Delete Order Menu");

		buttonPane = new HBox();
		buttonPane.getChildren().addAll(addBtn, updBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	
	void makeSubmitPane(Order order){
		User user = UserController.getCurrentUser();
		
		String proceedBtnName = new String();
		
		if(user.getUserRole().equals("Chef"))
			proceedBtnName = "Prepare Order";
		else if(user.getUserRole().equals("Waiter"))
			proceedBtnName = "Serve Order";
		
		proceedBtn = new Button(proceedBtnName);
		
		proceedPane = new VBox();
		proceedPane.getChildren().addAll(proceedBtn);
		proceedPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	void makeBackBtn(Order order){
		proceedBtn = new Button("back");

		buttonPane = new HBox();
		buttonPane.getChildren().addAll(proceedBtn);
	}
	
	public StackPane display(Stage s, Order order, BorderPane borderPane) {
		User user = UserController.getCurrentUser();
		boolean isCustomer = user.getUserRole().equals("Customer");
		
		if(!isCustomer) {
			makeForm(order);
		} else {
			makeBackBtn(order);
		}
		headerPane(order);
		if(!isCustomer) {
			makeSubmitPane(order);
		}
		
		makeTable(order, s, borderPane, isCustomer);
		

		VBox page = new VBox(10);
		if(!isCustomer)
			page.getChildren().addAll(headerPane, buttonPane, table, proceedPane);
		else 
			page.getChildren().addAll(buttonPane, headerPane, table);

		page.setPadding(new Insets(10));

		root = new StackPane();
		root.getChildren().add(page);
		
		ChefWaiterOrderDetailListController.refreshTableView(table, order);
		
		return root;
	}
}

