package controller.chefwaiter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.MGWindowController;
import controller.customer.CustomerListController;
import controller.model.OrderController;
import controller.model.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

	public static void addCustomerAction(
			Button proceedBtn,
			Stage s,
			BorderPane borderPane) 
	{
		proceedBtn.setOnAction(e ->{ 
			proceedBtn.setDisable(true);
			
			ChefWaiterMenuController.getChefOrderList(s, borderPane);
		});
	}
	
//	controller view
	public static StackPane setRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void definePagePane(boolean isCustomer, VBox pagePane, VBox headerPane, HBox buttonPane, TableView<OrderItem> table, VBox proceedPane) {
		if(!isCustomer)
			pagePane.getChildren().addAll(headerPane, buttonPane, table, proceedPane);
		else 
			pagePane.getChildren().addAll(buttonPane, headerPane, table);

		pagePane.setPadding(new Insets(10));
	}
	
	public static HBox defineButtonPane(HBox buttonPane, Button proceedBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(proceedBtn);
		return buttonPane;
	}
	
	public static VBox defineProceedPane(VBox proceedPane, Button proceedBtn) {
		proceedPane = new VBox();
		proceedPane.getChildren().addAll(proceedBtn);
		proceedPane.setAlignment(Pos.BOTTOM_RIGHT);
		return proceedPane;
	}

	public static String getProceedBtnName(User user, String proceedBtnName) {
		if(user.getUserRole().equals("Chef"))
			proceedBtnName = "Prepare Order";
		else if(user.getUserRole().equals("Waiter"))
			proceedBtnName = "Serve Order";
		return proceedBtnName;
	}
	
	public static VBox defineHeaderPane(VBox headerPane, Label orderIdLbl, Label userNameLbl, Label orderDateLbl, Label orderStatusLbl) {
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
	
	public static void getButtonAction(Order order, Stage s, BorderPane borderPane, boolean isCustomer, Button proceedBtn, Button addBtn, Button updBtn, Button deleteBtn, TableView<OrderItem> table, MenuItem currentMenu) {
		if(!isCustomer)
			ChefWaiterOrderDetailListController.addAction(
				proceedBtn,
				addBtn,
				updBtn,
				deleteBtn,
				order,
				table,
				currentMenu,
				s,
				borderPane);
		else
			ChefWaiterOrderDetailListController.addCustomerAction(
					proceedBtn,
					s,
					borderPane);
	}
}
