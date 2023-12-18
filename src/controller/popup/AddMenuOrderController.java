package controller.popup;

import controller.chefwaiter.ChefWaiterOrderDetailListController;
import controller.customer.CustomerOrderListController;
import controller.model.OrderController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ActivityLog;
import model.MGWindow;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import view.chefwaiter.ChefWaiterOrderDetailList;
import view.customer.CustomerOrderList;

public class AddMenuOrderController {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();

	public static void addButtonAction(MenuItem currentItem, Button btn, String input, MGWindow window, boolean isChef,
			boolean isWaiter, boolean isCustomer, Order order, Button confirmBtn, OrderItem item, TextField quantityTxt) {
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
	}
	
	public static Order getOrder(boolean isChef, boolean isWaiter, boolean isCustomer) {
		Order order;
		if(isChef || isWaiter) {
			order = OrderController.getOrder();
		} else if(isCustomer) {
			order = OrderController.getOnGoingOrder();
		} else {
			order = null;
		}
		return order;
	}
	
//	configure view
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 215);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	public static void configureBorderpane(BorderPane root, VBox headerPane, VBox quantityPane, HBox buttonPane) {
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(quantityPane);
		root.setBottom(buttonPane);
	}

	public static VBox defineQuantityPane(VBox quantityPane, Label quantityLbl, TextField quantityTxt) {
		quantityPane = new VBox();
		quantityPane.getChildren().addAll(quantityLbl, quantityTxt);
		quantityPane.setSpacing(15);
		return quantityPane;
	}

	public static void setQuantityTxt(OrderItem item, TextField quantityTxt) {
		quantityTxt.setText(String.valueOf(item == null ? 0 : item.getQuantity()));
	}

	public static HBox defineButtonPane(Button confirmBtn, HBox buttonPane) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		return buttonPane;
	}

	public static VBox defineHeaderPane(Label addPopup, Label content, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}

	public static void setLabelFont(Label addPopup, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		content.setFont(Font.font(null, 20));
	}
}
