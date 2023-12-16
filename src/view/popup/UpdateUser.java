package view.popup;

import javafx.scene.layout.BorderPane;
import controller.OrderItemController;
import controller.MGWindowController;
import controller.UserController.UserController;
import controller.admin.AdminUserListController;
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
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
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
		setLabelFont(addPopup, content);
		defineHeaderPane(addPopup, content);
		
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		defineHeaderPane(cancelBtn, confirmBtn);
		
		roleTxt = new TextField(currentUser.getUserRole());
		roleLbl = new Label("Role :");
		fillLbl = new Label("[‘Admin’, ‘Chef’, ‘Waiter’, ‘Cashier’, ‘Customer’]");

		defineRolePane();
		addButtonAction(window, cancelBtn, confirmBtn);
		configureBorderpane(root);
		
		StackPane container = new StackPane(root);
		setContainer(window, container);
		return container;
	}

	private static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 215);
		
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
		root.setCenter(rolePane);
		root.setBottom(buttonPane);
	}

	private static void addButtonAction(MGWindow window, Button cancelBtn, Button confirmBtn) {
		confirmBtn.setOnAction(
				e -> {
					if(roleTxt.getText().equals("Admin") || 
							roleTxt.getText().equals("Chef") || 
							roleTxt.getText().equals("Waiter")|| 
							roleTxt.getText().equals("Cashier")|| 
							roleTxt.getText().equals("Customer")
							) {
	//					Asumsi kami diharuskan untuk menggunakan updateUser pada class diagram.
	//					(sebab efisiennya dibuat function hanya untuk update role saja).
						Integer id = currentUser.getUserId();
						String role = roleTxt.getText();
						String name = currentUser.getUserName();
						String email = currentUser.getUserEmail();
						String password = currentUser.getUserPassword();
						
						UserController.updateUser(id, role, name, email, password);
	
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						
						TableView<User> table = AdminUserListController.getTable();
						AdminUserListController.refreshTableView(table);
					}
				}
		);
		
		cancelBtn.setOnAction(
				e -> {
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						
						TableView<User> table = AdminUserListController.getTable();
						AdminUserListController.refreshTableView(table);
						
				}
		);
	}

	private static void defineRolePane() {
		rolePane = new VBox();
		rolePane.getChildren().addAll(roleLbl, fillLbl, roleTxt);
		rolePane.setSpacing(5);
	}

	private static void defineHeaderPane(Button cancelBtn, Button confirmBtn) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
	}

	private static void defineHeaderPane(Label addPopup, Label content) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
	}

	private static void setLabelFont(Label addPopup, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		content.setFont(Font.font(null, 20));
	}
	
}
