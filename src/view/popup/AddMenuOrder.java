package view.popup;

import javafx.scene.layout.BorderPane;
import controller.OrderController;
import controller.OrderItemController;
import controller.WindowController;
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
import view.MGWindow;
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
	
	public static StackPane show(MenuItem currentItem, Button btn, String input) {
		
		MGWindow window = WindowController.getWindow();
		
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
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem);
		
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
								OrderItem newOrderItem = new OrderItem(OrderController.getOnGoingOrderId(), currentItem, Integer.valueOf(quantityTxt.getText()));
								OrderController.getOnGoingOrderItems().add(newOrderItem);
							}
						} else {
							if(quantityTxt.getText().equals("0")) {
								OrderController.getOnGoingOrderItems().remove(item);
							} else {
								// cuma ganti value qty doang tidak cukup, entah mengapa harus di hapus dan create lagi baru bisa di refresh tablenya.
								OrderController.getOnGoingOrderItems().remove(item);
								OrderItem newOrderItem = new OrderItem(OrderController.getOnGoingOrderId(), currentItem, Integer.valueOf(quantityTxt.getText()));
								OrderController.getOnGoingOrderItems().add(newOrderItem);
							}
						}
						
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();	
						}
						btn.setDisable(false);
						if(input.equals("Update")) {
							TableView<OrderItem> table = CustomerOrderList.table;
							CustomerOrderListController.refreshTableView(table);
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
