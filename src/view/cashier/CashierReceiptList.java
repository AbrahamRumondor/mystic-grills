package view.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.cashier.CashierOrderListController;
import controller.cashier.CashierReceiptListController;
import controller.customer.CustomerMenuListController;

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


public class CashierReceiptList {
	public static StackPane root;

	public static TableView<Order> table;
	Button orderDetailBtn;
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

		ObservableList<Order> items = CashierReceiptListController.getAllData();
		CashierReceiptListController.defineOrderToTable(items, table);

		assignTableItemToLocal(s, borderPane);
		CashierReceiptListController.addAction(
				orderDetailBtn,
				currentOrder,
				table,
				s,
				borderPane);
	}

	void makeForm(){
		orderDetailBtn = new Button("View Receipt Details");
		
		HBox buttonPane = new HBox();
		CashierReceiptListController.createButtonPane(buttonPane, orderDetailBtn);
		formPane = CashierReceiptListController.createFormPane(buttonPane, formPane);
	}
	
	public StackPane display(Stage s, BorderPane borderPane) {
		makeForm();
		makeTable(s, borderPane);
		
		VBox pagePane = new VBox(10);
		CashierReceiptListController.createPagePane(pagePane, table, formPane);

		root = CashierReceiptListController.createRootStackpane(pagePane, root);
		return root;
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
				
				CashierReceiptListController.addAction(
						orderDetailBtn,
						currentOrder,
						table,
						s,
						borderPane);
			}
		});
	}

}
