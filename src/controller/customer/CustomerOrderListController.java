package controller.customer;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.MGWindowController;
import controller.model.OrderController;
import controller.model.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.customer.CustomerOrderList;
import view.popup.AddMenuOrder;
import view.popup.DeleteMenuOrder;

public class CustomerOrderListController {
			
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
			CustomerListController.displayCustomerMenu("Menu");
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
	
//	configure view
	public static void createFormBox(HBox buttonPane, VBox formPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	public static void createButtonPane(HBox buttonPane, Button addBtn, Button updBtn, Button deleteBtn) {
		buttonPane.getChildren().addAll(addBtn, updBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	

	public static void createSubmitPane(VBox submitPane, Button submitBtn) {
		submitPane.getChildren().addAll(submitBtn);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public static StackPane createRootStackpane(VBox page, StackPane root) {
		root = new StackPane();
		root.getChildren().add(page);
		return root;
	}

	public static void createPagePane(VBox page, VBox formPane, TableView<OrderItem> table, VBox submitPane) {
		page.getChildren().addAll(formPane, table, submitPane);
		page.setPadding(new Insets(10));
	}
	
	public static void defineOrderItemTable(ObservableList<OrderItem> items, TableView<OrderItem> table) {
		table.setItems(items);
	}
}
