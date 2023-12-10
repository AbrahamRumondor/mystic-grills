package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import view.customer.CustomerMenuList;
import view.popup.AddMenuOrder;

public class MenuItem {
	
	private Integer menuItemId;
	private String menuItemName;
	private String menuItemDescription;
	private Double menuItemPrice;
	
	public MenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
		super();
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPrice = menuItemPrice;
	}
	
	public static ArrayList<MenuItem>getAllMenuItems() {
		ArrayList<MenuItem> items = new ArrayList<>();
		
		String query = "SELECT * FROM menu_item";
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("menu_item_id"));
				String name = rs.getString("menu_item_name");
				String desc = rs.getString("menu_item_description");
				Double price = Double.valueOf(rs.getString("menu_item_price"));
				
				System.out.println(id + name + desc + price);
				
				items.add(new MenuItem(id, name, desc, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}

	public static boolean createMenuItem(String name, String description, String price ) {
		
		if(!validateMenuItemCreation(name)) return false;
		
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`menu_item` (`menu_item_name`, `menu_item_description`, `menu_item_price`) VALUES (?, ?, ?);";
		
		System.out.println(name + " " + price);
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setString(1, name);
			prep.setString(2, description);
			prep.setString(3, price);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
		
	}
	
	private static boolean validateMenuItemCreation(String name) {
		
		ArrayList<MenuItem> items = getAllMenuItems();
		
		for (MenuItem item : items) {
			if(item.menuItemName.equalsIgnoreCase(name)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static MenuItem getMenuItemById(Integer menuItemId) {
		ArrayList<MenuItem> items = getAllMenuItems();
		
		for (MenuItem item : items) {
			if(item.menuItemId == menuItemId) {
				return item;
			}
		}
	
		return null;
	}
	
	public Integer getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Integer menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getMenuItemDescription() {
		return menuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}

	public Double getMenuItemPrice() {
		return menuItemPrice;
	}

	public void setMenuItemPrice(Double menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}
	
}
