package controller.cashier;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.OrderController;
import controller.OrderItemController;
import controller.WindowController;
import controller.UserController.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
}
