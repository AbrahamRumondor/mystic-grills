package controller.popup;

import controller.MGWindowController;
import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import controller.model.MenuItemController;
import controller.model.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ActivityLog;
import model.MGWindow;
import model.MenuItem;
import model.User;

public class DeletePopupController {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void setContent(Integer id, Label content, String target) {
		if(target.equals("User")) {
			content.setText(UserController.getUserNameById(id) + " ?");
		} else if(target.equals("Menu Item")) {
			content.setText(MenuItemController.getMenuItemNameById(id) + " ?");
		}
	}
	
	public static void setDeleteConfirmBtn(Integer id, Button confirmBtn, Button updateBtn, String target, Button addBtn) {
		MGWindow window = MGWindowController.getWindow();
		
		confirmBtn.setOnAction(
				e -> {
					if(target.equals("User")) {
						UserController.deleteUser(id);
						TableView<User> table = AdminUserListController.getTable();
						AdminUserListController.refreshTableView(table);
					} else if(target.equals("Menu Item")) {
						MenuItemController.deleteMenuItem(id);
						TableView<MenuItem> table = AdminMenuListController.getTable();
						AdminMenuListController.refreshTableView(table);
						addBtn.setDisable(false);
					}

					if(activityLog.getSceneStack().size() > 1) {
						window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
        				activityLog.pop();
					}
					
				}
		);
	}
	
	public static void addOnCancelAction(String target, Button addBtn, MGWindow window, Button cancelBtn) {
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
	
//	configure view
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 195);
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	public static void configureBorderpane(BorderPane root, VBox headerPane, VBox quantityPane, HBox buttonPane) {
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(quantityPane);
		root.setBottom(buttonPane);
	}

	public static HBox defineButtonPane(Button cancelBtn, Button confirmBtn, HBox buttonPane) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		return buttonPane;
	}

	public static VBox defineHeaderPane(Label addPopup, Label deleteMsg, Label content, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, deleteMsg, content);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}

	public static void setLabelFont(Label addPopup, Label deleteMsg, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		deleteMsg.setFont(Font.font(null, 16));
		content.setFont(Font.font(null, 20));
	}
}
