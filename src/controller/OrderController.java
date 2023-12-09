package controller;

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
	
	public static Integer getLatestOrderId() {
		return Order.getLastOrderId();
	}
	
	public static void setOngoingOrder(User user) {
		onGoingOrder = new Order(getLatestOrderId()+1, user, new ArrayList<OrderItem>() , null );
	}

	public static Order getOnGoingOrder() {
		return onGoingOrder;
	}

	public static void setOnGoingOrder(Order onGoingOrder) {
		OrderController.onGoingOrder = onGoingOrder;
	}
	
	public static boolean createOrder(User user, ArrayList<OrderItem> orderItems, Date orderDate) {
		
		return Order.createOrder(user, orderItems, orderDate);
		
	}
	
}
