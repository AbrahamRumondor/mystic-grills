package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Connect;
import model.MenuItem;
import model.Order;
import model.OrderItem;

public class OrderItemController {
	
	public static OrderItem getOrderItemInList(MenuItem currentItem, Order order) {
		for(OrderItem i : order.getOrderItems()) {
			if(i.getMenuItem().getMenuItemId() == currentItem.getMenuItemId()) {
				return i;
			}
		}
		
		return null;
	}
	
//	controller model
	public static boolean createOrderItem(Integer orderId, Integer menuItemId, Integer orderItemQuantity ) {
		return OrderItem.createOrderItem(orderId, menuItemId, orderItemQuantity);
	}
	
	public static void updateOrderItem(Integer orderId, MenuItem menuItem, Integer quantity) {
		OrderItem.updateOrderItem(orderId, menuItem, quantity);
	}
	
	public static void deleteOrderItem(Integer orderId, MenuItem menuItem) {
		OrderItem.deleteOrderItem(orderId, menuItem);
	}
	
	public static ArrayList<OrderItem>getAllOrderItemsByOrderId(Integer orderId) {
		return OrderItem.getAllOrderItemsByOrderId(orderId);
	}
	
}
