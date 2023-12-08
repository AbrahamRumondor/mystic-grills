package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MenuItemController;
import controller.UserController.UserController;

public class Order {
	private Integer orderId;
	private User orderUser;
	private ArrayList<OrderItem> orderItems;
	private Date orderDate;
	private Double orderTotal; // buat function khusus
	
	public Order(Integer orderId, User orderUser, ArrayList<OrderItem> orderItems, Date orderDate) {
		super();
		this.orderId = orderId;
		this.orderUser = orderUser;
		this.orderDate = orderDate;
		this.orderItems = orderItems; 
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
	public static boolean createOrder(Integer userId, Date orderDate ) {
		
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`order` (`user_id`, `order_status`, `order_date`) VALUES (?, ?, ?);";
		
		System.out.println(userId);
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, userId);
			prep.setString(2, "Pending");
			prep.setDate(3, orderDate);
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

	public User getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(User orderUser) {
		this.orderUser = orderUser;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	
	
	
	
	
}
