package view.popup.menuitem;

import javafx.scene.layout.BorderPane;
import controller.MenuItemController;
import controller.OrderItemController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.customer.CustomerOrderListController;
import controller.popup.MenuItemPopupController;
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

public class MenuItemPopup {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, addItemPane;
	static HBox buttonPane;
	static Label nameLbl, descLbl, priceLbl, fillLbl;
	static TextField nameTxt, descTxt, priceTxt;
	
	public static StackPane show(Button btn, Button updateBtn, Button deleteBtn, MenuItem chosenMenu, String action) {		
		
		MGWindow window = MGWindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label(action + " Menu");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		buttonPane = new HBox();
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		
		nameLbl = new Label("Menu name :");
		nameTxt = new TextField();
		
		descLbl = new Label("Menu description :");
		descTxt = new TextField();
		
		priceLbl = new Label("Menu price :");
		priceTxt = new TextField();
		
		addItemPane = new VBox();
		addItemPane.getChildren().addAll(
				nameLbl, nameTxt,
				descLbl, descTxt,
				priceLbl, priceTxt
				);
		addItemPane.setSpacing(5);
		
		if(action.equals("Add New"))
			MenuItemPopupController.addMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, btn);
		else if(action.equals("Update")) {
			MenuItemPopupController.updateMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, chosenMenu, deleteBtn, updateBtn, btn);
		}
		
		cancelBtn.setOnAction(
				e -> {
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						btn.setDisable(false);
						
						TableView<MenuItem> table = AdminMenuListController.getTable();
						AdminMenuListController.refreshTableView(table);
						
				}
		);
		
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(addItemPane);
		root.setBottom(buttonPane);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(300, 270);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
		return container;
	}
	
}
