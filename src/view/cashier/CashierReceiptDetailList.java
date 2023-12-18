package view.cashier;

import controller.cashier.CashierReceiptDetailListController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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


public class CashierReceiptDetailList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	VBox namePane, passwordPane, idPane, totalPricePane, headerPane;
	Label orderIdLbl, userNameLbl, orderDateLbl, orderStatusLbl, totalLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	Integer orderId;
	
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

		ObservableList<OrderItem> items = CashierReceiptDetailListController.getAllData(order);
		CashierReceiptDetailListController.defineOrderItemToTable(items, table);

		assignTableItemToLocal();
	}
	
	void headerPane(Order order) {
		 orderIdLbl = new Label("Order Id: " + order.getOrderId());
		 userNameLbl = new Label("Order Client: " + order.getOrderUser().getUserName());
		 orderDateLbl =  new Label("Order Date: " + order.getOrderDateString());
		 orderStatusLbl = new Label("Order Status: " + order.getOrderStatus());
		 CashierReceiptDetailListController.setLabelFont(
				 orderIdLbl,
				 userNameLbl,
				 orderDateLbl,
				 orderStatusLbl);
		 headerPane = CashierReceiptDetailListController.createHeaderPane(
				 headerPane,
				 orderIdLbl, 
				 userNameLbl, 
				 orderDateLbl,
				 orderStatusLbl);
	}
	
	void makeSubmitPane(Order order){
		String total = order.getOrderTotalString();
		totalLbl = new Label("Total Price: $" + total);
		totalLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
		
		totalPricePane = new VBox();
		totalPricePane.getChildren().addAll(totalLbl);
		totalPricePane.setSpacing(10);
		totalPricePane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public StackPane display(Stage s, Order order, BorderPane borderPane) {
		headerPane(order);
		makeSubmitPane(order);
		makeTable(order);
		
		VBox pagePane = new VBox(10);
		CashierReceiptDetailListController.createPagePane(pagePane, headerPane, table, totalPricePane);

		root = CashierReceiptDetailListController.createRootStackpane(pagePane, root);
		CashierReceiptDetailListController.refreshTableView(table, order);
		
		return root;
	}

	private void assignTableItemToLocal() {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				orderId = newValue.getOrderId();
			}
		});
	}
	
}

