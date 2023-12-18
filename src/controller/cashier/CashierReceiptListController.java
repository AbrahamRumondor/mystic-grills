package controller.cashier;

import java.util.ArrayList;

import controller.model.OrderController;
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
	
//	configure view

	public static StackPane createRootStackpane(VBox pagePane, StackPane root) {
		root = new StackPane();
		root.getChildren().add(pagePane);
		return root;
	}

	public static void createPagePane(VBox pagePane, TableView<Order> table, VBox formPane) {
		pagePane.getChildren().addAll(table, formPane);
		pagePane.setPadding(new Insets(10));
	}
	
	public static VBox createFormPane(HBox buttonPane, VBox formPane) {
		formPane = new VBox(10);
		formPane.getChildren().addAll(buttonPane);
		return formPane;
	}

	public static void createButtonPane(HBox buttonPane, Button orderDetailBtn) {
		buttonPane.getChildren().addAll(orderDetailBtn);
		buttonPane.setSpacing(15);
		buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public static void defineOrderToTable(ObservableList<Order> items, TableView<Order> table) {
		table.setItems(items);
	}
}
