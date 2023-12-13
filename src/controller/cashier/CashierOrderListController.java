package controller.cashier;

import java.util.ArrayList;

import controller.OrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.Order;
import model.User;
import view.admin.AdminUserList;
import view.cashier.CashierOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.DeletePopup;
import view.popup.UpdateUser;

public class CashierOrderListController {
	
	public static ObservableList<Order> getAllData() {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		
		ArrayList<Order> arrayOrders = OrderController.getAllOrders();
		
		for (Order order : arrayOrders) {
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
			Button updateBtn,
			Order currentOrder,
			TableView<Order> table,
			Button deleteBtn)
	{
		if(currentOrder == null) {
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
		}
		else {
			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
		updateBtn.setOnAction(e ->{ 
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
//			UpdateUser.show(currentOrder.getUserId(), updateBtn);
		});
		
		deleteBtn.setOnAction(e ->{ 
			updateBtn.setDisable(true);
			deleteBtn.setDisable(true);
//			DeletePopup.show(currentOrder.getUserId(),deleteBtn, updateBtn, "User", null);
		});
		
	}
	
	public static TableView<Order> getTable(){
		return CashierOrderList.table;
	}
	
}
