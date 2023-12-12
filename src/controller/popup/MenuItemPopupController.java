package controller.popup;

import controller.MenuItemController;
import controller.WindowController;
import controller.admin.AdminMenuListController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.ActivityLog;
import model.MenuItem;
import view.MGWindow;
import view.Notification;

public class MenuItemPopupController {
	
	
	public static void addMenuItem(
			TextField nameTxt, 
			TextField descTxt, 
			TextField priceTxt, 
			Button confirmBtn, 
			Button btn) 
	{
		MGWindow window = WindowController.getWindow();
		
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
	
	private static ActivityLog activityLog = ActivityLog.getInstance();
	
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
		MGWindow window = WindowController.getWindow();
		
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
	
	
	
}
