package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
import controller.admin.AdminUserListController;
import controller.customer.CustomerOrderListController;
import controller.model.UserController;
import controller.popup.UpdateUserController;
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
import model.ActivityLog;

public class UpdateUser {
		
	static VBox namePane, headerPane, rolePane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, roleLbl, fillLbl;
	static TextField nameTxt, descriptionTxt, roleTxt;
	
	static User currentUser;
	
	public static StackPane show(Integer userId, Button btn) {
		currentUser = UserController.getUserById(userId);
		
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Update User");
		Label content = new Label(currentUser.getUserName());
		UpdateUserController.setLabelFont(addPopup, content);
		headerPane = UpdateUserController.defineHeaderPane(addPopup, content, headerPane);
		
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane = UpdateUserController.defineButtonPane(cancelBtn, confirmBtn, buttonPane);
		
		roleTxt = new TextField(currentUser.getUserRole());
		roleLbl = new Label("Role :");
		fillLbl = new Label("[‘Admin’, ‘Chef’, ‘Waiter’, ‘Cashier’, ‘Customer’]");

		rolePane = UpdateUserController.defineRolePane(rolePane, roleLbl, fillLbl, roleTxt);
		UpdateUserController.addButtonAction(window, cancelBtn, confirmBtn, roleTxt, currentUser);
		UpdateUserController.configureBorderpane(root, headerPane, rolePane, buttonPane);
		
		StackPane container = new StackPane(root);
		UpdateUserController.setContainer(window, container);
		return container;
	}
}
