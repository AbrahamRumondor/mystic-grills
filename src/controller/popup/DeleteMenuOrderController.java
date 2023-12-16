package controller.popup;

import controller.chefwaiter.ChefWaiterOrderDetailListController;
import controller.customer.CustomerOrderListController;
import controller.model.OrderController;
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
import model.ActivityLog;
import model.MGWindow;
import model.Order;
import model.OrderItem;
import model.User;
import view.chefwaiter.ChefWaiterOrderDetailList;

public class DeleteMenuOrderController {

	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void addButtonAction(Button btn, Order order, MGWindow window, Button confirmBtn, OrderItem item, User user) {
		confirmBtn.setOnAction(
				e -> {
					order.getOrderItems().remove(item);

					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
					}
					btn.setDisable(false);
					
					if(user.getUserRole().equals("Customer")) {
						TableView<OrderItem> table = CustomerOrderListController.getTable();
						CustomerOrderListController.refreshTableView(table);
					} else if(user.getUserRole().equals("Chef")) {
						Integer idx = order.getOrderId();
						OrderController.deleteOrder(idx);
						OrderController.createOrderWithCertainId(idx, order.getOrderUser(), order.getOrderItems(), order.getOrderDate());
						
						TableView<OrderItem> table = ChefWaiterOrderDetailList.table;
						ChefWaiterOrderDetailListController.refreshTableView(table, order);
					}
				}
		);
	}
	
	public static Order getOrder(User user) {
		Order order;
		if(user.getUserRole().equals("Chef")) {
			order = OrderController.getOrder();
		} else if(user.getUserRole().equals("Customer")) {
			order = OrderController.getOnGoingOrder();
		} else {
			order = null;
		}
		return order;
	}
	
//	configure view
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 195);
		
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

	public static HBox defineButtonPane(Button confirmBtn, HBox buttonPane) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		return buttonPane;
	}

	public static VBox defineHeaderPane(Label addPopup, Label deleteMsg, Label content, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, deleteMsg, content);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}

	public static void setLabelFont(Label addPopup, Label deleteMsg, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		deleteMsg.setFont(Font.font(null, 16));
		content.setFont(Font.font(null, 20));
	}
}
