package view.cashier;

import controller.cashier.CashierViewOrderDetailListController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Order;
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
		CashierViewOrderDetailListController.defineOrderItemToTable(items, table);

		assignTableItemToLocal(order);
		CashierViewOrderDetailListController.addAction(submitBtn, order, table);
	}
	
	void headerPane(Order order) {
		 orderIdLbl = new Label("Order Id: " + order.getOrderId());
		 userNameLbl = new Label("Order Client: " + order.getOrderUser().getUserName());
		 orderDateLbl =  new Label("Order Date: " + order.getOrderDateString());
		 orderStatusLbl = new Label("Order Status: " + order.getOrderStatus());
	
		 CashierViewOrderDetailListController.setLabelFont(
				 orderIdLbl,
				 userNameLbl,
				 orderDateLbl,
				 orderStatusLbl);
		 headerPane = CashierViewOrderDetailListController.createHeaderPane(
				headerPane,
				orderIdLbl, 
				userNameLbl, 
				orderDateLbl,
				orderStatusLbl);
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

		VBox pagePane = new VBox(10);
		CashierViewOrderDetailListController.createPagePane(pagePane, headerPane, table, submitPane);

		root = CashierViewOrderDetailListController.createRootStackpane(pagePane, root);
		
		CashierViewOrderDetailListController.refreshTableView(table, order);
		return root;
	}

	private void assignTableItemToLocal(Order order) {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				orderId = newValue.getOrderId();
				CashierViewOrderDetailListController.addAction(submitBtn, order, table);

			}
		});
	}
	
}

