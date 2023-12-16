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

public class DeleteMenuOrder {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	private static User user = UserController.getCurrentUser();

	
	public static StackPane show(MenuItem currentItem, Button btn) {
		Order order;
		order = getOrder();
		
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Delete Menu");
		Label deleteMsg = new Label("Are you sure you want to delete ");
		Label content = new Label(currentItem.getMenuItemName() + "?");
		setLabelFont(addPopup, deleteMsg, content);
		
		defineHeaderPane(addPopup, deleteMsg, content);
		
		Button confirmBtn = new Button("Confirm");
		defineButtonPane(confirmBtn);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem, order);

		addButtonAction(btn, order, window, confirmBtn, item);
		
		configureBorderpane(root);
		
		StackPane container = new StackPane(root);
		setContainer(window, container);
		return container;
	}


	private static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 195);
		
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


	private static void addButtonAction(Button btn, Order order, MGWindow window, Button confirmBtn, OrderItem item) {
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


	private static void defineButtonPane(Button confirmBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
	}


	private static void defineHeaderPane(Label addPopup, Label deleteMsg, Label content) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, deleteMsg, content);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
	}


	private static void setLabelFont(Label addPopup, Label deleteMsg, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		deleteMsg.setFont(Font.font(null, 16));
		content.setFont(Font.font(null, 20));
	}


	private static Order getOrder() {
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
	
}
