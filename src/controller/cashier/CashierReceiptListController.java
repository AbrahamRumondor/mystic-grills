package controller.cashier;

import java.util.ArrayList;

import controller.OrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ActivityLog;
import model.MenuItem;
import model.Order;
import model.User;
import view.admin.AdminUserList;
import view.cashier.CashierOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.DeletePopup;
import view.popup.ProceedOrderPopup;
import view.popup.UpdateUser;

public class CashierReceiptListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static ObservableList<Order> getAllData() {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		
		ArrayList<Order> arrayOrders = OrderController.getAllOrders();
		
		for (Order order : arrayOrders) {
			if(order.getOrderStatus().equals("Paid"))
				orders.add(order);
		}
		return orders;
	}
	
	public static void refreshTableView(TableView<Order> table) {
		ObservableList<Order> users = FXCollections.observableArrayList();
		users = getAllData();
		table.setItems(users);
	}
	
	public static void addAction(
			Button orderDetailBtn,
			Order currentOrder,
			TableView<Order> table,
			Stage s,
			BorderPane borderPane)
	{
		if(currentOrder == null) {
			orderDetailBtn.setDisable(true);
		}
		else {
			orderDetailBtn.setDisable(false);
		}
		
		orderDetailBtn.setOnAction(e ->{ 
			orderDetailBtn.setDisable(true);
			
			StackPane contents = CashierReceiptDetailListController.display(s, currentOrder, borderPane);
			borderPane.setCenter(contents);
			activityLog.add(contents);
		});
		
	}
	
	public static TableView<Order> getTable(){
		return CashierOrderList.table;
	}
	
}
