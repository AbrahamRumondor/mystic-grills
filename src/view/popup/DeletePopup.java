package view.popup;

import javafx.scene.layout.BorderPane;
import controller.OrderItemController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.customer.CustomerOrderListController;
import controller.popup.DeletePopupController;
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
import view.admin.AdminUserList;
import view.customer.CustomerOrderList;
import view.guest.GuestDefault;
import view.guest.GuestLogin;
import model.ActivityLog;

public class DeletePopup {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	static User user;
	
	public static StackPane show(Integer id, Button deleteBtn, Button updateBtn, String target, Button addBtn) {
		
		user = UserController.getUserById(id);
		
		MGWindow window = MGWindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Delete " + target);
		Label deleteMsg = new Label("Are you sure you want to delete ");
		Label content = new Label();
		setLabelFont(addPopup, deleteMsg, content);
		
		DeletePopupController.setContent(id, content, target);
		
		defineHeaderPane(addPopup, deleteMsg, content);
	
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		defineButtonPane(cancelBtn, confirmBtn);
		
		DeletePopupController.setDeleteConfirmBtn(id, confirmBtn, updateBtn, target, addBtn);

		addOnCancelAction(target, addBtn, window, cancelBtn);
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

	private static void addOnCancelAction(String target, Button addBtn, MGWindow window, Button cancelBtn) {
		cancelBtn.setOnAction(
				e -> {
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						
						if(target.equals("Menu Item")) {
							addBtn.setDisable(false);
							TableView<MenuItem> table = AdminMenuListController.getTable();
							AdminMenuListController.refreshTableView(table);
						}
						else if(target.equals("User")) {
							TableView<User> table = AdminUserListController.getTable();
							AdminUserListController.refreshTableView(table);
						}
				}
		);
	}

	private static void defineButtonPane(Button cancelBtn, Button confirmBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
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
	
}
