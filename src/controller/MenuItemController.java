package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.TextField;
import model.Connect;
import model.MenuItem;
import view.Notification;

public class MenuItemController {
	
	public static ArrayList<MenuItem> getAllMenuItems() {
		return MenuItem.getAllMenuItems();
	}
	
	public static boolean createMenuItem(String name, String description, String price ) {
		return MenuItem.createMenuItem(name, description, price);
	}
	
	public static MenuItem getMenuItemById(Integer menuItemId) {
		return MenuItem.getMenuItemById(menuItemId);
	}
	
	public static MenuItem getMenuItemByName(String menuName) {
		return MenuItem.getMenuItemByName(menuName);
	}
	
	public static boolean updateMenuItem(Integer id, String name, String description, String price ) {
		return MenuItem.updateMenuItem(id, name, description, price);
	}
	
	public static void deleteMenuItem(Integer menuItemId) {
		MenuItem.deleteMenuItem(menuItemId);
	}
	
	public static String getMenuItemNameById(Integer menuItemId) {
		return MenuItem.getMenuItemNameById(menuItemId);
	}
	
	public static boolean isValidMenuItemFields(
			String name,
			String desc,
			Double price,
			MenuItem isNewMenu
			) {
		
		if(name.isEmpty() || desc.isEmpty()) {
			Notification.showErrorMessage("Please fill in all fields");
			return false;
		} else
			if(!(isNewMenu == null)) {
			Notification.showErrorMessage("Name already exists in database");
			return false;
		} else
		if(desc.length() <= 10) {
			Notification.showErrorMessage("Description must be more than 10 chars");
			return false;
		} else
		if(Double.valueOf(price) < 2.5) {
			Notification.showErrorMessage("Price must be a number and atleast 2.5");
			return false;
		}
		
		return true;
	}
}
