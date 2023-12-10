package view;

import javafx.scene.layout.BorderPane;
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
import view.customer.CustomerOrderList;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class DeleteMenu {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	public static StackPane show(MenuItem currentItem, Button btn) {
		
		MGWindow window = WindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Delete Menu");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		
		Label deleteMsg = new Label("Are you sure you want to delete ");
		Label content = new Label(currentItem.getMenuItemName() + "?");
		deleteMsg.setFont(Font.font(null, 16));
		content.setFont(Font.font(null, 20));
		
		
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, deleteMsg, content);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		buttonPane = new HBox();
		Button confirmBtn = new Button("Confirm");
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		
		OrderItem item = OrderItemController.getOrderItemInList(currentItem);
		

		confirmBtn.setOnAction(
				e -> {
					Order.getOrderItems().remove(item);

					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
					}
					btn.setDisable(false);
					
					TableView<OrderItem> table = CustomerOrderList.table;
					CustomerOrderListController.refreshTableView(table);
				}
		);
		
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(quantityPane);
		root.setBottom(buttonPane);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(300, 195);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
        
        
		return container;
	}
	
}
