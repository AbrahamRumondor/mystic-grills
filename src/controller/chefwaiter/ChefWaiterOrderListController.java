package controller.chefwaiter;

import java.util.ArrayList;

import controller.OrderController;
import controller.UserController.UserController;
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
import view.admin.AdminUserList;
import view.cashier.CashierOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;
import view.popup.DeletePopup;
import view.popup.ProceedOrderPopup;
import view.popup.UpdateUser;

public class ChefWaiterOrderListController {
	
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static ObservableList<Order> getAllData() {
		User user = UserController.getCurrentUser();
		ObservableList<Order> ordersC = FXCollections.observableArrayList();
		ObservableList<Order> ordersW = FXCollections.observableArrayList();

		ArrayList<Order> arrayOrders = OrderController.getAllOrders();
		
		for (Order order : arrayOrders) {
			
			if(user.getUserRole().equals("Chef")) {
				System.out.println("SAYA CHEF");
				if(order.getOrderStatus().equals("Pending")) {
					ordersC.add(order);
				}
			}
			
			if(user.getUserRole().equals("Waiter")) {
				if(order.getOrderStatus().equals("Prepared")) {
					ordersW.add(order);
				}
			}
			
		}
		
		if(user.getUserRole().equals("Chef")) {
			return ordersC;
		}else {
			return ordersW;
		}
		
	}
	
	public static void refreshTableView(TableView<Order> table) {
		ObservableList<Order> users = FXCollections.observableArrayList();
		users = getAllData();
		table.setItems(users);
	}
	
	public static void addAction(
			Button orderDetailBtn,
			Order order,
			TableView<Order> table,
			Button proceedBtn,
			Stage s,
			BorderPane borderPane)
	{	
		User user = UserController.getCurrentUser();
		
		if(order == null) {
			orderDetailBtn.setDisable(true);
			proceedBtn.setDisable(true);
		}
		else {
			orderDetailBtn.setDisable(false);
			proceedBtn.setDisable(false);
		}
		
		orderDetailBtn.setOnAction(e ->{ 
			orderDetailBtn.setDisable(true);
			proceedBtn.setDisable(true);
			
			StackPane contents = ChefWaiterOrderDetailListController.display(s, order, borderPane);
			borderPane.setCenter(contents);
			activityLog.add(contents);
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
	
	
}
