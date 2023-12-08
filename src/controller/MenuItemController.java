package controller;

import java.util.ArrayList;

import model.MenuItem;

public class MenuItemController {
	
	public static ArrayList<MenuItem> getAllUsers() {
		return MenuItem.getAllMenuItems();
	}
	
	public static boolean createMenuItem(String name, String description, String price ) {
		return MenuItem.createMenuItem(name, description, price);
	}
	
	public static MenuItem getMenuItemById(Integer menuItemId) {
		return MenuItem.getMenuItemById(menuItemId);
	}
}
