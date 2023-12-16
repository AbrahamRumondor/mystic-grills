package controller.cashier;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.MGWindowController;
import controller.model.OrderItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.cashier.CashierReceiptDetailList;
import view.cashier.CashierViewOrderDetailList;
import view.customer.CustomerOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.ProceedOrderPopup;

public class CashierReceiptDetailListController {
		
	private static CashierReceiptDetailList cashierReceiptDetailList = new CashierReceiptDetailList();
	
	public static void refreshTableView(TableView<OrderItem> table, Order order) {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		items = getAllData(order);
		table.setItems(items);
	}

	public static ObservableList<OrderItem> getAllData(Order order) {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		
		ArrayList<OrderItem> arrayItems = OrderItemController.getAllOrderItemsByOrderId(order.getOrderId());
		
		for (OrderItem item : arrayItems) {
			items.add(item);
		}
		
		return items;
	}

	public static TableView<OrderItem> getTable(){
		return CustomerOrderList.table;
	}
	
	public static StackPane display(Stage s, Order order, BorderPane borderPane) {
		return cashierReceiptDetailList.display(s, order, borderPane);
	}
	
//	configure view
	public static StackPane createRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void createPagePane(VBox pagePane, VBox headerPane, TableView<OrderItem> table, VBox totalPricePane) {
		pagePane.getChildren().addAll(headerPane, table, totalPricePane);
		pagePane.setPadding(new Insets(10));
	}
	
	public static VBox createHeaderPane(VBox headerPane, Label orderIdLbl, Label userNameLbl, Label orderDateLbl, Label orderStatusLbl) {
		headerPane = new VBox();
		 headerPane.getChildren().addAll(
				 orderIdLbl, 
				 userNameLbl, 
				 orderDateLbl,
				 orderStatusLbl);
		 headerPane.setPadding(new Insets(10));
		 return headerPane;
	}

	public static void setLabelFont(Label orderIdLbl, Label userNameLbl, Label orderDateLbl, Label orderStatusLbl) {
		orderIdLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 orderDateLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
		 orderStatusLbl.setFont(Font.font(null, FontWeight.BOLD, 14));
	}
	
	public static void defineOrderItemToTable(ObservableList<OrderItem> items, TableView<OrderItem> table) {
		table.setItems(items);
	}
}
