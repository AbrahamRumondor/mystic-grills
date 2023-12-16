package view.chefwaiter;

import controller.chefwaiter.ChefWaiterOrderListController;
import controller.model.UserController;
import java.sql.Date;
import javafx.collections.ObservableList;
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
import model.Order;

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
		ChefWaiterOrderListController.defineOrderToTable(items, table);

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
		proceedBtnName = ChefWaiterOrderListController.getProceedBtnName(user, proceedBtnName);
		
		proceedBtn = new Button(proceedBtnName);

		HBox buttonPane = new HBox();
		ChefWaiterOrderListController.defineButtonPane(isCustomer, buttonPane, orderDetailBtn, proceedBtn);
		formPane = ChefWaiterOrderListController.defineFormPane(buttonPane, formPane);
	}
	
	public StackPane display(Stage s, BorderPane borderPane) {
		makeForm();
		makeTable(s, borderPane);
		
		VBox pagePane = new VBox(10);
		ChefWaiterOrderListController.definePagePane(pagePane, table, formPane);

		root = ChefWaiterOrderListController.setRootStackpane(pagePane, root);
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
}
