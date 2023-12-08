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
	
	public OrderItem(Integer orderId, MenuItem menuItem, Integer quantity) {
		super();
		this.orderId = orderId;
		this.menuItem = menuItem;
		this.quantity = quantity;
	}
	
	public static ArrayList<OrderItem>getAllOrderItemsByOrderId(Integer orderId) {
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		
		String query = "SELECT * FROM order_item where order_id = ?";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, orderId);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("order_id"));
				Integer itemId = Integer.valueOf(rs.getString("menu_item_id"));
				Integer qty = Integer.valueOf(rs.getString("order_item_quantity"));;
				
				MenuItem item = MenuItemController.getMenuItemById(itemId);
				
				System.out.println(id + itemId + qty);
				
				orderItems.add(new OrderItem(id, item, qty));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	
}
