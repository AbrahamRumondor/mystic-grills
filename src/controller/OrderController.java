package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		onGoingOrder = new Order(getLatestOrderId(), user, new ArrayList<OrderItem>() , null );
	}

	public static Order getOnGoingOrder() {
		return onGoingOrder;
	}

	public static void setOnGoingOrder(Order onGoingOrder) {
		OrderController.onGoingOrder = onGoingOrder;
	}
	
	
	
}
