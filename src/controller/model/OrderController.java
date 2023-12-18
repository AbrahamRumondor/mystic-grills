package controller.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Connect;
import model.Order;
import model.OrderItem;
import model.User;

public class OrderController {
	
	static Order onGoingOrder;
	
	static Order order;
	
	public static void setOrder(Order currentOrder) {
		order = currentOrder;
	}
	
	public static Order getOrder() {
		return order;
	}
	
	public static Integer getLatestOrderId() {
		return Order.getLastOrderId();
	}
	
	public static void setOngoingOrder(User user) {
		onGoingOrder = new Order(getLatestOrderId()+1, user, new ArrayList<OrderItem>() , "Pending", null );
	}

	public static Order getOnGoingOrder() {
		return onGoingOrder;
	}

	public static void setOnGoingOrder(Order onGoingOrder) {
		OrderController.onGoingOrder = onGoingOrder;
	}
	
	public static Integer getOnGoingOrderId() {
		return onGoingOrder.getOrderId();
	}
	
	public static User getOnGoingOrderUser() {
		return onGoingOrder.getOrderUser();
	}

	
//	controller model
	public static boolean createOrder(User user, ArrayList<OrderItem> orderItems, Date orderDate) {
		
		return Order.createOrder(user, orderItems, orderDate);
		
	}
	
	public static void updateOrder(Integer orderId, ArrayList<OrderItem> orderItems, String string) {
		// TODO Auto-generated method stub
		Order.updateOrder(orderId, orderItems, string);
	}

	public static ArrayList<Order>getOrdersByCustomerId(Integer customerId) {
		return Order.getOrdersByCustomerId(customerId);
	}
	
	public static void deleteOrder(Integer orderId) {
		Order.deleteOrder(orderId);
	}
	
	public static ArrayList<Order>getAllOrders() {
		return Order.getAllOrders();
	}
	
	public static boolean createOrderWithCertainId(Integer userId, User user, ArrayList<OrderItem> orderItems, Date orderDate) {
		return Order.createOrderWithCertainId(userId, user, orderItems, orderDate);
	}
		
	public static Order getOrderByOrderId(Integer orderId) {
		return Order.getOrderByOrderId(orderId);
	}
	
	public static ArrayList<OrderItem> getOnGoingOrderItems() {
		return onGoingOrder.getOrderItems();
	}
	
//	controller view
	
}
