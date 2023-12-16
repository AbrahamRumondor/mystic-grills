package controller.popup;

import controller.MGWindowController;
import controller.admin.AdminMenuListController;
import controller.model.MenuItemController;
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
import model.MenuItem;
import view.popup.Notification;

public class MenuItemPopupController {
		
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
	public static void addMenuItem(
			TextField nameTxt, 
			TextField descTxt, 
			TextField priceTxt, 
			Button confirmBtn, 
			Button btn) 
	{
		MGWindow window = MGWindowController.getWindow();
		
		confirmBtn.setOnAction(
				e -> {
						
					String name = nameTxt.getText();
					String desc = descTxt.getText();
					Double price;
					try {
					    price = Double.valueOf(priceTxt.getText());

					} catch (NumberFormatException ex) {
					    price = (double) -1;
					}
					
					
					MenuItem isNewMenu = MenuItemController.getMenuItemByName(name);
					
					boolean isValidMenu = MenuItemController.isValidMenuItemFields(name, desc, price, isNewMenu);
					
					if(isValidMenu) {
						MenuItemController.createMenuItem(name, desc, price.toString());
						
						TableView<MenuItem> table = AdminMenuListController.getTable();
						AdminMenuListController.refreshTableView(table);
						
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						btn.setDisable(false);
					}
	
				}
		);
	}
	
	public static void updateMenuItem(
			TextField nameTxt, 
			TextField descTxt, 
			TextField priceTxt, 
			Button confirmBtn, 
			MenuItem chosenMenu, 
			Button deleteBtn, 
			Button updateBtn, 
			Button btn) 
	{
		MGWindow window = MGWindowController.getWindow();
		
		nameTxt.setText(chosenMenu.getMenuItemName());
		descTxt.setText(chosenMenu.getMenuItemDescription());
		priceTxt.setText(chosenMenu.getMenuItemPrice().toString());
		
		confirmBtn.setOnAction(
				e -> {
						
					String name = nameTxt.getText();
					String desc = descTxt.getText();
					Double price;
					try {
					    price = Double.valueOf(priceTxt.getText());

					} catch (NumberFormatException ex) {
					    price = (double) -1;
					}
					
					
					MenuItem isNewMenu = MenuItemController.getMenuItemByName(name);
					
					if(chosenMenu.getMenuItemName().equals(nameTxt.getText())){
						isNewMenu = null;
					}
					
					boolean isValidMenu = MenuItemController.isValidMenuItemFields(name, desc, price, isNewMenu);
					
					if(isValidMenu) {
						System.out.println(desc);
						MenuItemController.updateMenuItem(chosenMenu.getMenuItemId(), name, desc, price.toString());
						
						TableView<MenuItem> table = AdminMenuListController.getTable();
						AdminMenuListController.refreshTableView(table);
						
						if(activityLog.getSceneStack().size() > 1) {
							window.root.getChildren().remove(activityLog.getSceneStack().lastElement());
	        				activityLog.pop();
						}
						btn.setDisable(false);
						deleteBtn.setDisable(false);
						updateBtn.setDisable(false);
					}
	
				}
		);
		
	}
	
	public static void addButtonAction(Button btn, MGWindow window, Button cancelBtn) {
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

	public static void getAction(Button btn, Button updateBtn, Button deleteBtn, MenuItem chosenMenu, String action,
			Button confirmBtn, TextField nameTxt, TextField descTxt, TextField priceTxt) {
		if(action.equals("Add New"))
			MenuItemPopupController.addMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, btn);
		else if(action.equals("Update")) {
			MenuItemPopupController.updateMenuItem(nameTxt, descTxt, priceTxt, confirmBtn, chosenMenu, deleteBtn, updateBtn, btn);
		}
	}
	
//	configure view
	public static void defineContainer(MGWindow window, StackPane container) {
		container.setMaxSize(300, 270);
		container.setStyle("-fx-background-color: #f4f4f4;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 1px;");
		StackPane.setMargin(container, new Insets(10,10,10,10));
        window.root.getChildren().add(container);
        activityLog.getSceneStack().add(container);
	}

	public static void configureBorderpane(BorderPane root, VBox headerPane, VBox addItemPane, HBox buttonPane) {
		root.setPadding(new Insets(20, 20, 20, 20));
		root.setTop(headerPane);
		root.setCenter(addItemPane);
		root.setBottom(buttonPane);
	}

	public static VBox defineAddItemPane(VBox addItemPane, Label nameLbl, TextField nameTxt, Label descLbl, TextField descTxt, Label priceLbl, TextField priceTxt) {
		addItemPane = new VBox();
		addItemPane.getChildren().addAll(
				nameLbl, nameTxt,
				descLbl, descTxt,
				priceLbl, priceTxt
				);
		addItemPane.setSpacing(5);
		return addItemPane;
	}

	public static HBox defineButtonPane(Button cancelBtn, Button confirmBtn, HBox buttonPane) {
		buttonPane = new HBox();
		buttonPane.getChildren().addAll(cancelBtn, confirmBtn);
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.BOTTOM_CENTER);
		return buttonPane;
	}

	public static VBox defineHeaderPane(Label addPopup, VBox headerPane) {
		headerPane = new VBox();
		headerPane.getChildren().addAll(addPopup);
		headerPane.setSpacing(10);
		headerPane.setAlignment(Pos.TOP_CENTER);
		return headerPane;
	}

	public static void setLabelFont(Label addPopup) {
		Font font = Font.font(null, FontWeight.BOLD, 20);
		addPopup.setFont(font);
	}
}
