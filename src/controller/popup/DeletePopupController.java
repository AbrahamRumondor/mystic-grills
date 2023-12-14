package controller.popup;

import controller.MenuItemController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.admin.AdminMenuListController;
import controller.admin.AdminUserListController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.ActivityLog;
import model.MenuItem;
import model.User;
import view.MGWindow;

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
	
}
