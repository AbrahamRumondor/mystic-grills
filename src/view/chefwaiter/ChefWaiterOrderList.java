package view.chefwaiter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.UserController.UserController;
import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.cashier.CashierOrderListController;
import controller.chefwaiter.ChefWaiterOrderListController;

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
import javafx.stage.Stage;
import model.User;
import view.popup.AddMenuOrder;
import model.MenuItem;
import model.Order;
import model.Connect;



public class ChefWaiterOrderList {
	public static StackPane root;

	
	public static TableView<Order> table;
	Button orderDetailBtn, proceedBtn;
	VBox formPane, namePane, passwordPane, idPane;
	Label nameLbl, emailLbl, roleLbl;
	TextField nameTxt, emailTxt, roleTxt;
	
	Integer orderId;
	User orderUser;
	String orderStatus;
	Date orderDate;
	Double totalPrice;
	
	Order currentOrder;
	
	private void makeTable(Stage s, BorderPane borderPane) {
		table = new TableView<>();

		TableColumn<Order, Integer> idColumn = new TableColumn<>("Order Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId" ));
		TableColumn<Order, String> nameColumn = new TableColumn<>("Client");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderUserName" ));
		TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		TableColumn<Order, Date> dateColumn = new TableColumn<>("Submit Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate" ));
		TableColumn<Order, String> totalColumn = new TableColumn<>("Total Price");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotalString"));
		
		table.getColumns().addAll(idColumn, nameColumn, statusColumn, dateColumn, totalColumn);

		ObservableList<Order> items = ChefWaiterOrderListController.getAllData();
		defineOrderToTable(items);

		assignTableItemToLocal(s, borderPane);
		ChefWaiterOrderListController.addAction(
				orderDetailBtn,
				currentOrder,
				table,
				proceedBtn,
				s,
				borderPane);
	}

	void makeForm(){
		User user = UserController.getCurrentUser();
		boolean isCustomer = user.getUserRole().equals("Customer");
		
		orderDetailBtn = new Button("View Order Details");
		
		String proceedBtnName = new String();
		proceedBtnName = getProceedBtnName(user, proceedBtnName);
		
		proceedBtn = new Button(proceedBtnName);

		HBox buttonPane = new HBox();
		defineButtonPane(isCustomer, buttonPane);
		defineFormPane(buttonPane);
	}
	
	public StackPane display(Stage s, BorderPane borderPane) {
		makeForm();
		makeTable(s, borderPane);
		
		VBox pagePane = new VBox(10);
		definePagePane(pagePane);

		setRootStackpane(pagePane);
		return root;
	}

	private void setRootStackpane(VBox pagePane) {
		root = new StackPane();
		root.getChildren().add(pagePane);
	}

	private void definePagePane(VBox page) {
		page.getChildren().addAll(table, formPane);
		page.setPadding(new Insets(10));
	}
	
	private void defineOrderToTable(ObservableList<Order> items) {
		table.setItems(items);
	}

	private void assignTableItemToLocal(Stage s, BorderPane borderPane) {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentOrder = newValue;
				orderId = newValue.getOrderId();
				orderUser = newValue.getOrderUser();
				orderStatus = newValue.getOrderStatus();
				orderDate = newValue.getOrderDate();
				totalPrice = newValue.getOrderTotal();
				
				ChefWaiterOrderListController.addAction(
						orderDetailBtn,
						currentOrder,
						table,
						proceedBtn,
						s,
						borderPane);
			}
		});
	}
	
	private void defineFormPane(HBox buttonPane) {
		formPane = new VBox(10);
		formPane.getChildren().addAll(buttonPane);
	}

	private void defineButtonPane(boolean isCustomer, HBox buttonPane) {
		if(!isCustomer)
			buttonPane.getChildren().addAll(orderDetailBtn, proceedBtn);
		else
			buttonPane.getChildren().addAll(orderDetailBtn);
		
		buttonPane.setSpacing(15);
		buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
	}

	private String getProceedBtnName(User user, String proceedBtnName) {
		if(user.getUserRole().equals("Chef"))
			proceedBtnName = "Prepare Order";
		else if(user.getUserRole().equals("Waiter"))
			proceedBtnName = "Serve Order";
		return proceedBtnName;
	}

}
