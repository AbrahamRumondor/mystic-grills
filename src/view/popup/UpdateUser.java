package view.popup;

import javafx.scene.layout.BorderPane;
import controller.OrderItemController;
import controller.WindowController;
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
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.MGWindow;
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
		
		System.out.println(currentUser == null);
		
		MGWindow window = WindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Update User");
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		
		Label content = new Label(currentUser.getUserName());
		content.setFont(Font.font(null, 20));
		
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		buttonPane = new HBox();
		Button confirmBtn = new Button("Confirm");
		buttonPane.getChildren().addAll(confirmBtn);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		
		roleTxt = new TextField(currentUser.getUserRole());
		roleLbl = new Label("Role :");
		fillLbl = new Label("[‘Admin’, ‘Chef’, ‘Waiter’, ‘Cashier’, ‘Customer’]");
//		priceTxt.setDisable(true);
		
		rolePane = new VBox();
		rolePane.getChildren().addAll(roleLbl, fillLbl, roleTxt);
		rolePane.setSpacing(5);
		
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
						btn.setDisable(false);
						
						TableView<User> table = AdminUserListController.getTable();
						AdminUserListController.refreshTableView(table);
					}
				}
		);
		
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(rolePane);
		root.setBottom(buttonPane);
		
		StackPane container = new StackPane(root);
		container.setMaxSize(300, 215);
		
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
		
		return container;
	}
	
}
