package controller.customer;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.OrderController;
import controller.WindowController;
import controller.UserController.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.customer.CustomerOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;

public class CustomerOrderListController {
	
	private static WindowController windowController = WindowController.getInstance();
		
	public static void refreshTableView(TableView<OrderItem> table) {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		items = getAllData();
		table.setItems(items);
	}

	public static ObservableList<OrderItem> getAllData() {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		
		ArrayList<OrderItem> arrayItems = OrderController.getOnGoingOrderItems();
		
		for (OrderItem item : arrayItems) {
			items.add(item);
		}
		
		return items;
	}
	
	public static void addAction(String orderQty, Button updBtn, Button deleteBtn, Button submitBtn, Button addBtn, MenuItem currentItem, TableView<OrderItem> table){
		if(orderQty == null) {
			updBtn.setDisable(true);
			deleteBtn.setDisable(true);
		}
		else {
			updBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
		if(OrderController.getOnGoingOrderItems().size() == 0) submitBtn.setDisable(true);
		
		updBtn.setOnAction(e ->{ 
			updBtn.setDisable(true);
			AddMenuOrder.show(currentItem,updBtn, "Update");
			refreshTableView(table);
		});
		
		deleteBtn.setOnAction(e ->{ 
			deleteBtn.setDisable(true);
			DeleteMenuOrder.show(currentItem,deleteBtn);
		});
		
		addBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			CustomerMenuController.displayCustomerMenu("Menu");
		});
		
		submitBtn.setOnAction(e ->{ 
			submitBtn.setDisable(true);
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date orderDate = Date.valueOf(currentDateTime.toLocalDate());
			
			OrderController.createOrder(OrderController.getOnGoingOrderUser(), OrderController.getOnGoingOrderItems(), orderDate);
			
//			reset order trus back to main menu
			User user = UserController.getCurrentUser();
			OrderController.setOngoingOrder(user);
			CustomerDefaultController.goToCustomerDefault();
			
		});
		
		
	}

	public static TableView<OrderItem> getTable(){
		return CustomerOrderList.table;
	}
}
