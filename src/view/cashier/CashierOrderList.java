package view.cashier;

import controller.cashier.CashierOrderListController;
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

public class CashierOrderList {
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

		ObservableList<Order> items = CashierOrderListController.getAllData();
		CashierOrderListController.defineOrderToTable(items, table);

		assignTableItemToLocal(s, borderPane);
		CashierOrderListController.addAction(
				orderDetailBtn,
				currentOrder,
				table,
				proceedBtn,
				s,
				borderPane);
	}

	void makeForm(){
		orderDetailBtn = new Button("View Order Details");
		proceedBtn = new Button("Proceed Order");

		HBox buttonPane = new HBox();
		CashierOrderListController.createButtonPane(buttonPane, orderDetailBtn, proceedBtn);
		formPane = new VBox(10);
		CashierOrderListController.createFormPane(buttonPane, formPane);
	}
	
	public StackPane display(Stage s, BorderPane borderPane) {
		makeForm();
		makeTable(s, borderPane);
		
		VBox pagePane = new VBox(10);
		CashierOrderListController.createPagePane(pagePane, table, formPane);

		root = CashierOrderListController.createRootStackpane(pagePane, root);
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
				
				
				CashierOrderListController.addAction(
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
