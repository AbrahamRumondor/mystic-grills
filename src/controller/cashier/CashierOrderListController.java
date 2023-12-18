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
import model.Order;
import view.cashier.CashierOrderList;
import view.popup.ProceedOrderPopup;

public class CashierOrderListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static ObservableList<Order> getAllData() {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		
		ArrayList<Order> arrayOrders = OrderController.getAllOrders();
		
//		karena kita cuma punya status yg bisa buat control flow order
//		maka sebaiknya hanya bisa bayar saat sudah served.
		for (Order order : arrayOrders) {
			if(order.getOrderStatus().equals("Served"))
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
			Button proceedBtn,
			Stage s,
			BorderPane borderPane)
	{
		if(currentOrder == null) {
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
			
			StackPane contents = CashierViewOrderDetailListController.display(s, currentOrder, borderPane);
			borderPane.setCenter(contents);
			activityLog.add(contents);
		});
		
		proceedBtn.setOnAction(e ->{ 
			orderDetailBtn.setDisable(true);
			proceedBtn.setDisable(true);
			ProceedOrderPopup.show(currentOrder, proceedBtn, "Order");
			
			refreshTableView(table);
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

	public static void createFormPane(HBox buttonPane, VBox formPane) {
		formPane.getChildren().addAll(buttonPane);
	}

	public static void createButtonPane(HBox buttonPane, Button orderDetailBtn, Button proceedBtn) {
		buttonPane.getChildren().addAll(orderDetailBtn, proceedBtn);
		buttonPane.setSpacing(15);
		buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public static void defineOrderToTable(ObservableList<Order> items, TableView<Order> table) {
		table.setItems(items);
	}

}
