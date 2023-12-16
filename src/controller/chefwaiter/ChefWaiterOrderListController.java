package controller.chefwaiter;

import java.util.ArrayList;

import controller.model.OrderController;
import controller.model.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		ObservableList<Order> orders = FXCollections.observableArrayList();

		ArrayList<Order> arrayOrders = OrderController.getAllOrders();
		
		for (Order order : arrayOrders) {
			
//			get order u/ chef
			if(user.getUserRole().equals("Chef")) {
				if(order.getOrderStatus().equals("Pending"))
					orders.add(order);
			}
			
//			get order u/ waiter
			if(user.getUserRole().equals("Waiter")) {
				if(order.getOrderStatus().equals("Prepared"))
					orders.add(order);
			}
			
//			get order u/ customer
			if(user.getUserRole().equals("Customer")) {
				if(order.getOrderUser().getUserId() == user.getUserId()) 
					orders.add(order);
			}
			
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
	
//	configure view
	public static StackPane setRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void definePagePane(VBox page, TableView<Order> table, VBox formPane) {
		page.getChildren().addAll(table, formPane);
		page.setPadding(new Insets(10));
	}
	
	public static void defineOrderToTable(ObservableList<Order> items, TableView<Order> table) {
		table.setItems(items);
	}

	public static VBox defineFormPane(HBox buttonPane, VBox formPane) {
		formPane = new VBox(10);
		formPane.getChildren().addAll(buttonPane);
		return formPane;
	}

	public static void defineButtonPane(boolean isCustomer, HBox buttonPane, Button orderDetailBtn, Button proceedBtn) {
		if(!isCustomer)
			buttonPane.getChildren().addAll(orderDetailBtn, proceedBtn);
		else
			buttonPane.getChildren().addAll(orderDetailBtn);
		
		buttonPane.setSpacing(15);
		buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
	}

	public static String getProceedBtnName(User user, String proceedBtnName) {
		if(user.getUserRole().equals("Chef"))
			proceedBtnName = "Prepare Order";
		else if(user.getUserRole().equals("Waiter"))
			proceedBtnName = "Serve Order";
		return proceedBtnName;
	}
	
}
