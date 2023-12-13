package view.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.OrderController;
import controller.WindowController;
import controller.UserController.UserController;
import controller.cashier.CashierViewOrderDetailListController;
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


public class CashierViewOrderDetailList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, submitBtn;
	VBox namePane, passwordPane, idPane, submitPane, headerPane;
	Label orderIdLbl, userNameLbl, orderDateLbl, orderStatusLbl, totalLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	Integer orderId;
//	User orderUser;
//	Date orderDate;
//	String orderStatus;
	
	OrderItem currentItem;
	
	private void makeTable(Order order) {
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

		ObservableList<OrderItem> items = CashierViewOrderDetailListController.getAllData(order);
		table.setItems(items);

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				orderId = newValue.getOrderId();
				CashierViewOrderDetailListController.addAction(submitBtn, order, table);

			}
		});
		
		CashierViewOrderDetailListController.addAction(submitBtn, order, table);
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
	
	
	void makeSubmitPane(Order order){
		submitBtn = new Button("Submit Order");
		String total = order.getOrderTotalString();
		totalLbl = new Label("Total Price: $" + total);
		totalLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
		
		submitPane = new VBox();
		submitPane.getChildren().addAll(totalLbl, submitBtn);
		submitPane.setSpacing(10);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public StackPane display(Stage s, Order order, BorderPane borderPane) {
		headerPane(order);
		makeSubmitPane(order);
		makeTable(order);
		

		VBox page = new VBox(10);
		page.getChildren().addAll(headerPane, table, submitPane);
		page.setPadding(new Insets(10));

		root = new StackPane();
		root.getChildren().add(page);
		
		CashierViewOrderDetailListController.refreshTableView(table, order);
		
		return root;
	}
}

