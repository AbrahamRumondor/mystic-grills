package view.popup;

import javafx.scene.layout.BorderPane;
import controller.OrderController;
import controller.OrderItemController;
import controller.UserController.UserController;
import controller.chefwaiter.ChefWaiterOrderDetailListController;
import controller.MGWindowController;
import controller.customer.CustomerOrderListController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import view.MGWindow;
import view.chefwaiter.ChefWaiterOrderDetailList;
import view.customer.CustomerOrderList;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class AddMenuOrder {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	private static User user = UserController.getCurrentUser();
	
	public static StackPane show(MenuItem currentItem, Button btn, String input) {
		
		MGWindow window = MGWindowController.getWindow();
		
		Order order;
		
		boolean isChef = user.getUserRole().equals("Chef");
		boolean isWaiter = user.getUserRole().equals("Waiter");
		boolean isCustomer = user.getUserRole().equals("Customer");
		if(isChef || isWaiter) {
			order = OrderController.getOrder();
		} else if(isCustomer) {
			order = OrderController.getOnGoingOrder();
		} else {
			order = null;
		}
		
		System.out.println("order ==> " + order.getOrderId());
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label(input + " Menu");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		
		Label content = new Label(currentItem.getMenuItemName());
		content.setFont(Font.font(null, 20));
		
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		buttonPane = new HBox();
		Button confirmBtn = new Button("Confirm");
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem, order);
		
		quantityTxt = new TextField();
		quantityTxt.setText(String.valueOf(item == null ? 0 : item.getQuantity()));
		quantityLbl = new Label("Quantity :");
//		priceTxt.setDisable(true);
		
		quantityPane = new VBox();
		quantityPane.getChildren().addAll(quantityLbl, quantityTxt);
		quantityPane.setSpacing(15);
		
		confirmBtn.setOnAction(
				e -> {
					if(!quantityTxt.getText().equals("")) {		
						if(item == null) {
							if(!quantityTxt.getText().equals("0")) {
								OrderItem newOrderItem = new OrderItem(order.getOrderId(), currentItem, Integer.valueOf(quantityTxt.getText()));
								order.getOrderItems().add(newOrderItem);
								if(isChef || isWaiter) {
									Integer idx = order.getOrderId();
									OrderController.deleteOrder(idx);
									OrderController.createOrderWithCertainId(idx, order.getOrderUser(), order.getOrderItems(), order.getOrderDate());
								}
							}
						} else {
							if(quantityTxt.getText().equals("0")) {
								order.getOrderItems().remove(item);
								if(isChef || isWaiter) {
									Integer idx = order.getOrderId();
									OrderController.deleteOrder(idx);
									OrderController.createOrderWithCertainId(idx, order.getOrderUser(), order.getOrderItems(), order.getOrderDate());
								}
							} else {
								// cuma ganti value qty doang tidak cukup, entah mengapa harus di hapus dan create lagi baru bisa di refresh tablenya.
								order.getOrderItems().remove(item);
								OrderItem newOrderItem = new OrderItem(order.getOrderId(), currentItem, Integer.valueOf(quantityTxt.getText()));
								order.getOrderItems().add(newOrderItem);
								if(isChef || isWaiter) {
									Integer idx = order.getOrderId();
									OrderController.deleteOrder(idx);
									OrderController.createOrderWithCertainId(idx, order.getOrderUser(), order.getOrderItems(), order.getOrderDate());
								}
							}
						}
						
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();	
						}
						
						btn.setDisable(false);
						if(input.equals("Update")) {
							if(isCustomer) {
								TableView<OrderItem> table = CustomerOrderList.table;
								CustomerOrderListController.refreshTableView(table);
							} else if(isChef || isWaiter) {
								TableView<OrderItem> table = ChefWaiterOrderDetailList.table;
								ChefWaiterOrderDetailListController.refreshTableView(table, order);
							}
							
						}
					}
				}
		);
		
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(quantityPane);
		root.setBottom(buttonPane);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(300, 215);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
		return container;
	}
	
}
