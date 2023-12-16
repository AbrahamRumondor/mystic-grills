package controller.popup;

import controller.admin.AdminUserListController;
import controller.model.UserController;
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
import model.User;

public class UpdateUserController {
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void addButtonAction(MGWindow window, Button cancelBtn, Button confirmBtn, TextField roleTxt, User currentUser) {
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

//	configure view
	public static void setContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 215);
		
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

	public static VBox defineRolePane(VBox rolePane, Label roleLbl, Label fillLbl, TextField roleTxt) {
		rolePane = new VBox();
		rolePane.getChildren().addAll(roleLbl, fillLbl, roleTxt);
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

	public static VBox defineHeaderPane(Label addPopup, Label content, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup, content);
		headerPane.setSpacing(5);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}

	public static void setLabelFont(Label addPopup, Label content) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
		content.setFont(Font.font(null, 20));
	}
}
