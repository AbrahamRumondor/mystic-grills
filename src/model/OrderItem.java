package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MenuItemController;

public class OrderItem {
	
	private Integer orderId;
	private MenuItem menuItem;
	private Integer quantity;
	
//	Dua attribute ini dipakai khusus untuk dipakai buat print table. klo lsng lsng akses menuItem dia error.
//	private String menuName;
//	private Double totalPrice;
	
	public OrderItem(Integer orderId, MenuItem menuItem, Integer quantity) {
		super();
		this.orderId = orderId;
		this.menuItem = menuItem;
		this.quantity = quantity;
//		this.menuName = menuItem.getMenuItemName();
//		this.totalPrice = menuItem.getMenuItemPrice() * quantity;
	}
	
	public static ArrayList<OrderItem>getAllOrderItemsByOrderId(Integer orderId) {
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		
		String query = "SELECT * FROM order_item where order_id = " + orderId + ";";
		
//		PreparedStatement prep = Connect.getConnection().prepare(query);
//		try {
//			prep.setInt(1, orderId);
//			Connect.getConnection().executePreparedUpdate(prep);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		ArrayList<Integer> items = new ArrayList<>();
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("order_id"));
				Integer itemId = Integer.valueOf(rs.getString("menu_item_id"));
				Integer qty = Integer.valueOf(rs.getString("order_item_quantity"));;

				orderItems.add(new OrderItem(id, null, qty));
				items.add(itemId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < orderItems.size(); i++) {
			Integer itemId = items.get(i);
			MenuItem item = MenuItemController.getMenuItemById(itemId);
			orderItems.get(i).setMenuItem(item);
		}
		System.out.println("WOI LAH");
		return orderItems;
	}

	public static boolean createOrderItem(Integer orderId, Integer menuItemId, Integer orderItemQuantity ) {
		
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`order_item` (`order_id`, `menu_item_id`, `order_item_quantity`) VALUES (?, ?, ?);";
		
		System.out.println(orderId + " " + orderItemQuantity);
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, orderId);
			prep.setInt(2, menuItemId);
			prep.setInt(3, orderItemQuantity);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMenuName() {
		return menuItem.getMenuItemName();
	}

	public Double getTotalPrice() {
		return menuItem.getMenuItemPrice() * quantity;
	}
	
	
	
}
