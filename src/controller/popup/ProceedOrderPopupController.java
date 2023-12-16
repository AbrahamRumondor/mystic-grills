package controller.popup;

import java.sql.Date;
import java.time.LocalDateTime;

import controller.cashier.CashierOrderListController;
import controller.model.OrderController;
import controller.model.ReceiptController;
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
import model.Order;
import view.popup.Notification;

public class ProceedOrderPopupController {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();

	public static void addButtonAction(Order order, Button btn, String position, MGWindow window, Button cancelBtn,
			Button confirmBtn, TextField typeTxt, TextField paymentTxt) {
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
	}

//	configure view	
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 300);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}


	public static void configureBorderpane(BorderPane root, VBox headerPane, VBox rolePane, HBox buttonPane) {
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(rolePane);
		root.setBottom(buttonPane);
	}

	public static VBox defineRolePane(VBox rolePane, Label typeLbl, Label fillLbl, TextField typeTxt, Label paymentLbl, TextField paymentTxt) {
		rolePane = new VBox();
		rolePane.getChildren().addAll(
				typeLbl,
				fillLbl,
				typeTxt,
				paymentLbl,
				paymentTxt
				);
		rolePane.setSpacing(5);
		return rolePane;
	}


	public static HBox defineButtonPane(Button cancelBtn, Button confirmBtn, HBox buttonPane) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		return buttonPane;
	}


	public static VBox defineHeaderPane(Label addPopup, Label content, Label spacing, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content, spacing);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}


	public static void setLabelFont(Label addPopup, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		content.setFont(Font.font(null, 18));
	}
	
}
