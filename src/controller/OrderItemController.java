package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Connect;
import model.MenuItem;
import model.Order;
import model.OrderItem;

public class OrderItemController {
	
	public static OrderItem getOrderItemInList(MenuItem currentItem) {
		
		for(OrderItem i : Order.getOrderItems()) {
			if(i.getMenuItem().getMenuItemId() == currentItem.getMenuItemId()) {
				return i;
			}
		}
		
		return null;
	}
	
	public static boolean createOrderItem(Integer orderId, Integer menuItemId, Integer orderItemQuantity ) {
		return OrderItem.createOrderItem(orderId, menuItemId, orderItemQuantity);
	}
	
}
