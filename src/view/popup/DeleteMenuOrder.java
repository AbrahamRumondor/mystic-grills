package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
import controller.model.OrderItemController;
import controller.model.UserController;
import controller.popup.DeleteMenuOrderController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MGWindow;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;

public class DeleteMenuOrder {
		
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	private static User user = UserController.getCurrentUser();

	
	public static StackPane show(MenuItem currentItem, Button btn) {
		Order order;
		order = DeleteMenuOrderController.getOrder(user);
		
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Delete Menu");
		Label deleteMsg = new Label("Are you sure you want to delete ");
		Label content = new Label(currentItem.getMenuItemName() + "?");
		DeleteMenuOrderController.setLabelFont(addPopup, deleteMsg, content);
		
		headerPane = DeleteMenuOrderController.defineHeaderPane(addPopup, deleteMsg, content, headerPane);
		
		Button confirmBtn = new Button("Confirm");
		buttonPane = DeleteMenuOrderController.defineButtonPane(confirmBtn, buttonPane);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem, order);

		DeleteMenuOrderController.addButtonAction(btn, order, window, confirmBtn, item, user);
		
		DeleteMenuOrderController.configureBorderpane(root,headerPane, quantityPane, buttonPane);
		
		StackPane container = new StackPane(root);
		DeleteMenuOrderController.setContainer(window, container);
		return container;
	}
}
