package view.popup;

import javafx.scene.layout.BorderPane;
import controller.chefwaiter.ChefWaiterOrderDetailListController;
import controller.MGWindowController;
import controller.customer.CustomerOrderListController;
import controller.model.OrderController;
import controller.model.OrderItemController;
import controller.model.UserController;
import controller.popup.AddMenuOrderController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import model.User;
import view.chefwaiter.ChefWaiterOrderDetailList;
import view.customer.CustomerOrderList;

public class AddMenuOrder {
		
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
		order = AddMenuOrderController.getOrder(isChef, isWaiter, isCustomer);
		
		Label addPopup = new Label(input + " Menu");
		Label content = new Label(currentItem.getMenuItemName());
		AddMenuOrderController.setLabelFont(addPopup, content);
		headerPane = AddMenuOrderController.defineHeaderPane(addPopup, content, headerPane);
		
		Button confirmBtn = new Button("Confirm");
		buttonPane = AddMenuOrderController.defineButtonPane(confirmBtn, buttonPane);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem, order);
		
		quantityTxt = new TextField();
		AddMenuOrderController.setQuantityTxt(item, quantityTxt);
		quantityLbl = new Label("Quantity :");
		quantityPane = AddMenuOrderController.defineQuantityPane(quantityPane, quantityLbl, quantityTxt );
		
		AddMenuOrderController.addButtonAction(currentItem, btn, input, window, isChef, isWaiter, isCustomer, order, confirmBtn, item, quantityTxt);
		
		AddMenuOrderController.configureBorderpane(root, headerPane, quantityPane, buttonPane);
		
		StackPane container = new StackPane(root);
		AddMenuOrderController.setContainer(window, container);
		return container;
	}
}
