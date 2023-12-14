package view.popup;

import javafx.scene.layout.BorderPane;

import java.sql.Date;
import java.time.LocalDateTime;

import controller.OrderController;
import controller.OrderItemController;
import controller.ReceiptController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.admin.AdminUserListController;
import controller.cashier.CashierOrderListController;
import controller.cashier.CashierViewOrderDetailListController;
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
import view.Notification;
import view.customer.CustomerOrderList;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class ProceedOrderPopup {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, rolePane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, typeLbl, fillLbl, paymentLbl;
	static TextField nameTxt, descriptionTxt, typeTxt, paymentTxt;

	
	public static StackPane show(
			Order order,
			Button btn,
			String position) {
		
		
		MGWindow window = MGWindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Proceed Order");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		
		Label content = new Label("Total Payment: " + order.getOrderTotalString());
		content.setFont(Font.font(null, 18));
		
		Label spacing = new Label("");
		
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content, spacing);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		buttonPane = new HBox();
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		
		typeTxt = new TextField();
		typeLbl = new Label("Payment Type :");
		fillLbl = new Label("[‘Cash’, ‘Debit’, ‘Credit’]");
			
		paymentLbl = new Label("Payment amount :");
		paymentTxt = new TextField("");
		
		rolePane = new VBox();
		rolePane.getChildren().addAll(
				typeLbl,
				fillLbl,
				typeTxt,
				paymentLbl,
				paymentTxt
				);
		rolePane.setSpacing(5);
		
		confirmBtn.setOnAction(
				e -> {
					boolean isValidType = false;
					boolean isValidAmount = false;
					
					if(typeTxt.getText().equals("Cash") ||
							typeTxt.getText().equals("Debit") ||
							typeTxt.getText().equals("Credit")
							) {
						isValidType = true;
					} else {
						Notification.showErrorMessage("please only insert [‘Cash’, ‘Debit’, ‘Credit’]");
					}
					
					Double totalCharge;
					try {
					    totalCharge = Double.valueOf(paymentTxt.getText());

					} catch (NumberFormatException ex) {
					    totalCharge = (double) -1;
					}
					
					if(totalCharge >= order.getOrderTotal()) {
						isValidAmount = true;
					} else {
						Notification.showErrorMessage("Payment cannot be less than total charge");
					}
					
					
					
					if(isValidType && isValidAmount) {
						
						LocalDateTime currentDateTime = LocalDateTime.now();
						Date orderDate = Date.valueOf(currentDateTime.toLocalDate());
						
						OrderController.updateOrder(order.getOrderId(), order.getOrderItems(), "Paid");
						ReceiptController.createReceipt(order, typeTxt.getText(), totalCharge, orderDate);
						
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						
						if(position.equals("Order")) {
							TableView<Order> table = CashierOrderListController.getTable();
							CashierOrderListController.refreshTableView(table);
						} else {
							btn.setDisable(false);
						}
						
					}
				}
		);
		
		cancelBtn.setOnAction(
				e -> {
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						
						if(position.equals("Order")) {
							TableView<Order> table = CashierOrderListController.getTable();
							CashierOrderListController.refreshTableView(table);
						} else {
							btn.setDisable(false);
						}
				}
		);
		
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(rolePane);
		root.setBottom(buttonPane);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(300, 300);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
		return container;
	}
	
}

