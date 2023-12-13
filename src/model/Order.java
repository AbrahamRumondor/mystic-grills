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
	private Integer orderId;
	private User orderUser;
	private ArrayList<OrderItem> orderItems;
	private String orderStatus;
	private Date orderDate;
	private Double orderTotal; // buat function khusus
	
	public Order(Integer id, User user, ArrayList<OrderItem> items, String status, Date date) {
		super();
		orderId = id;
		orderUser = user;
		orderDate = date;
		orderItems = items; 
		orderStatus = status;
		
//		if(!(date == null)){
//			setOrderTotal();
//		}
	}
	
	public static ArrayList<Order>getAllOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM mystic_grills.order;";
		
		ArrayList<Integer> userIds = new ArrayList<>();
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer id = Integer.valueOf(rs.getString("order_id"));
				Integer userId = Integer.valueOf(rs.getString("user_id"));
				String status = rs.getString("order_status");
				Date date = rs.getDate("order_date");
				System.out.println(id + userId + status + date);
				
				userIds.add(userId);
				orders.add(new Order(id, null, null, status, date));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < orders.size(); i++) {
			Integer userId = userIds.get(i);
			User user = UserController.getUserById(userId);
			orders.get(i).setOrderUser(user);
		}
		
		
		for(int i = 0; i < orders.size(); i++) {
			orders.get(i);
			Integer orderId = orders.get(i).getOrderId();
			ArrayList<OrderItem> items = OrderItemController.getAllOrderItemsByOrderId(orderId);
			orders.get(i).setOrderItems(items);
			orders.get(i).setOrderTotal(items);
		}
		
		for(Order i : orders) {
			System.out.println(i.getOrderId());
		}
		
		return orders;
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
				
				ArrayList<OrderItem> orderItms = OrderItemController.getAllOrderItemsByOrderId(id); // buat controllernya
				User user = UserController.getUserById(userId);
				
				System.out.println(id + userId + orderStatus);
				
				orders.add(new Order(id, user, orderItms, orderStatus, orderDate));
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

	public String getOrderUserName() {
		return orderUser.getUserName();
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
	
	public String getOrderDateString() {
		return orderDate.toString();
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}
	
	public String getOrderTotalString() {
		return orderTotal.toString();
	}
	
	public void setOrderTotal(ArrayList<OrderItem> items) {
		Double totalPrice = (double) 0;
		
		for(OrderItem item : items) {
			totalPrice += item.getTotalPrice();
		}
		
		this.orderTotal = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
	
	
	
}
