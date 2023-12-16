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
import model.MGWindow;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
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
		BorderPane root = new BorderPane();
		
		boolean isChef = user.getUserRole().equals("Chef");
		boolean isWaiter = user.getUserRole().equals("Waiter");
		boolean isCustomer = user.getUserRole().equals("Customer");
		
		Order order;
		order = getOrder(isChef, isWaiter, isCustomer);
		
		Label addPopup = new Label(input + " Menu");
		Label content = new Label(currentItem.getMenuItemName());
		setLabelFont(addPopup, content);
		defineHeaderPane(addPopup, content);
		
		Button confirmBtn = new Button("Confirm");
		defineButtonPane(confirmBtn);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem, order);
		
		quantityTxt = new TextField();
		setQuantityTxt(item);
		quantityLbl = new Label("Quantity :");
		defineQuantityPane();
		
		addButtonAction(currentItem, btn, input, window, isChef, isWaiter, isCustomer, order, confirmBtn, item);
		
		configureBorderpane(root);
		
		StackPane container = new StackPane(root);
		setContainer(window, container);
		return container;
	}

	private static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 215);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	private static void configureBorderpane(BorderPane root) {
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(quantityPane);
		root.setBottom(buttonPane);
	}

	private static void addButtonAction(MenuItem currentItem, Button btn, String input, MGWindow window, boolean isChef,
			boolean isWaiter, boolean isCustomer, Order order, Button confirmBtn, OrderItem item) {
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

	private static void defineQuantityPane() {
		quantityPane = new VBox();
		quantityPane.getChildren().addAll(quantityLbl, quantityTxt);
		quantityPane.setSpacing(15);
	}

	private static void setQuantityTxt(OrderItem item) {
		quantityTxt.setText(String.valueOf(item == null ? 0 : item.getQuantity()));
	}

	private static void defineButtonPane(Button confirmBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
	}

	private static void defineHeaderPane(Label addPopup, Label content) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
	}

	private static void setLabelFont(Label addPopup, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		content.setFont(Font.font(null, 20));
	}

	private static Order getOrder(boolean isChef, boolean isWaiter, boolean isCustomer) {
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
	
}
