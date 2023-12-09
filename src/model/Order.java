package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.MenuItemController;
import controller.OrderItemController;
import controller.UserController.UserController;

public class Order {
	private static Integer orderId;
	private static User orderUser;
	private static ArrayList<OrderItem> orderItems;
	private static Date orderDate;
	private static Double orderTotal; // buat function khusus
	
	public Order(Integer id, User user, ArrayList<OrderItem> items, Date date) {
		super();
		orderId = id;
		orderUser = user;
		orderDate = date;
		orderItems = items; 
	}
	
	public static Integer getLastOrderId() {
		
		String query = "SELECT MAX(order_id) AS 'max_order_id' FROM mystic_grills.order;";
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			if (rs.next()) {
		        return rs.getInt("max_order_id");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<Order>getOrdersByCustomerId(Integer customerId) {
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM order where user_id = ?";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, customerId);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("order_id"));
				Integer userId = Integer.valueOf(rs.getString("user_id"));
				String orderStatus = rs.getString("order_status");
				Date orderDate = Date.valueOf(rs.getString("order_date"));
				
				ArrayList<OrderItem> orderItms = OrderItem.getAllOrderItemsByOrderId(id); // buat controllernya
				User user = UserController.getUserById(userId);
				
				System.out.println(id + userId + orderStatus);
				
				orders.add(new Order(id, user, orderItms, orderDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
//	alasan parameternya berbeda adalah karena ini akan dimasukkan di database, sehingga tidak bisa pakai yang seperti di diagram.
	public static boolean createOrder(User user, ArrayList<OrderItem> orderItems, Date orderDate) {
		
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`order` (`user_id`, `order_status`, `order_date`) VALUES (?, ?, ?);";
		
		System.out.println(user);
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, user.getUserId());
			prep.setString(2, "Pending");
			prep.setDate(3, orderDate);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(OrderItem item : orderItems) {
			OrderItemController.createOrderItem(item.getOrderId(), item.getMenuItem().getMenuItemId(), item.getQuantity());
		}
		
		return true;
		
	}

	public static Integer getOrderId() {
		return orderId;
	}

	public static void setOrderId(Integer orderId) {
		Order.orderId = orderId;
	}

	public static User getOrderUser() {
		return orderUser;
	}

	public static void setOrderUser(User orderUser) {
		Order.orderUser = orderUser;
	}

	public static ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public static void setOrderItems(ArrayList<OrderItem> orderItems) {
		Order.orderItems = orderItems;
	}

	public static Date getOrderDate() {
		return orderDate;
	}

	public static void setOrderDate(Date orderDate) {
		Order.orderDate = orderDate;
	}

	public static Double getOrderTotal() {
		return orderTotal;
	}

	public static void setOrderTotal(Double orderTotal) {
		Order.orderTotal = orderTotal;
	}
	
	
	
	
	
	
}
