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
import model.MGWindow;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.customer.CustomerOrderList;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import view.popup.Notification;
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
		setLabelFont(addPopup);
		defineHeaderPane(addPopup);
		
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		defineButtonPane(cancelBtn, confirmBtn);
		
		nameLbl = new Label("Menu name :");
		nameTxt = new TextField();
		
		descLbl = new Label("Menu description :");
		descTxt = new TextField();
		
		priceLbl = new Label("Menu price :");
		priceTxt = new TextField();
		
		defineAddItemPane();
		getAction(btn, updateBtn, deleteBtn, chosenMenu, action, confirmBtn);
		addButtonAction(btn, window, cancelBtn);
		
		configureBorderpane(root);
		
		StackPane container = new StackPane(root);
		defineContainer(window, container);
		
		return container;
	}

	private static void defineContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 270);
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
		root.setCenter(addItemPane);
		root.setBottom(buttonPane);
	}

	private static void addButtonAction(Button btn, MGWindow window, Button cancelBtn) {
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
	}

	private static void getAction(Button btn, Button updateBtn, Button deleteBtn, MenuItem chosenMenu, String action,
			Button confirmBtn) {
		if(action.equals("Add New"))
			MenuItemPopupController.addMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, btn);
		else if(action.equals("Update")) {
			MenuItemPopupController.updateMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, chosenMenu, deleteBtn, updateBtn, btn);
		}
	}

	private static void defineAddItemPane() {
		addItemPane = new VBox();
		addItemPane.getChildren().addAll(
				nameLbl, nameTxt,
				descLbl, descTxt,
				priceLbl, priceTxt
				);
		addItemPane.setSpacing(5);
	}

	private static void defineButtonPane(Button cancelBtn, Button confirmBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
	}

	private static void defineHeaderPane(Label addPopup) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
	}

	private static void setLabelFont(Label addPopup) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
	}
	
}
