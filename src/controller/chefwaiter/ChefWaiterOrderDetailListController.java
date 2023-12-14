package controller.chefwaiter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.OrderController;
import controller.OrderItemController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.customer.CustomerMenuController;
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
import model.OrderItem;
import model.User;
import view.cashier.CashierViewOrderDetailList;
import view.chefwaiter.ChefWaiterOrderDetailList;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.ProceedOrderPopup;

public class ChefWaiterOrderDetailListController {
		
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void refreshTableView(TableView<OrderItem> table, Order order) {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		items = getAllData(order);
		table.setItems(items);
	}

	public static ObservableList<OrderItem> getAllData(Order order) {
		ObservableList<OrderItem> items = FXCollections.observableArrayList();
		
		ArrayList<OrderItem> arrayItems = order.getOrderItems();
		
		for (OrderItem item : arrayItems) {
			items.add(item);
		}
		
		return items;
	}
	
	public static void addAction(
			Button proceedBtn,
			Button addBtn,
			Button updBtn,
			Button deleteBtn,
			Order order,
			TableView<OrderItem> table,
			MenuItem item, 
			Stage s, 
			BorderPane borderPane
			)
	{		
		User user = UserController.getCurrentUser();

		if(item == null) {
			updBtn.setDisable(true);
			deleteBtn.setDisable(true);
		}
		else {
			updBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		
//		if(OrderController.getOnGoingOrderItems().size() == 0) submitBtn.setDisable(true);
		
		updBtn.setOnAction(e ->{ 
			updBtn.setDisable(true);
			AddMenuOrder.show(item,updBtn, "Update");
			refreshTableView(table, order);
		});
		
		deleteBtn.setOnAction(e ->{ 
			deleteBtn.setDisable(true);
			DeleteMenuOrder.show(item,deleteBtn);
		});
		
		addBtn.setOnAction(e ->{ 
			addBtn.setDisable(true);
			CustomerMenuList customerMenuList = new CustomerMenuList();
			StackPane contents = customerMenuList.display(s);
			borderPane.setCenter(contents);
			activityLog.add(contents);
			
			refreshTableView(table, order);
		});
		
		proceedBtn.setOnAction(e ->{ 
			proceedBtn.setDisable(true);
			 
			Integer orderId = order.getOrderId();
			ArrayList<OrderItem> orderItems = order.getOrderItems();
			
			String status = new String();
			if(user.getUserRole().equals("Chef"))
				status = "Prepared";
			else if(user.getUserRole().equals("Waiter"))
				status = "Served";	
			
			OrderController.updateOrder(orderId, orderItems, status);
			
			ChefWaiterMenuController.displayChefMenu("Order");
		});
		
	}

	public static StackPane display(Stage s, Order order, BorderPane borderPane) {
		ChefWaiterOrderDetailList chefOrderDetailList = new ChefWaiterOrderDetailList();
		return chefOrderDetailList.display(s, order, borderPane);
	}
}
