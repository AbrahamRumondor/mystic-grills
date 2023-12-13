package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.OrderItemController;

public class Receipt {
	
	Integer receiptId;
	Order receiptOrder;
	Double receiptPaymentAmount;
	Date receiptPaymentDate;
	String receiptPaymentType;
	
	public Receipt(
			Integer receiptId,
			Order receiptOrder,
			Double receiptPaymentAmount,
			Date receiptPaymentDate,
			String receiptPaymentType) 
	{
		super();
		this.receiptId = receiptId;
		this.receiptOrder = receiptOrder;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate; 
		this.receiptPaymentType = receiptPaymentType;
	}
	
	public static boolean createReceipt(
			Order order,
			String receiptPaymentType,
			Double receiptPaymentAmount,
			Date receiptPaymentDate
			)
	{
		// pada '?', '' di VALUES dihilangin, nanti ? dianggapnya sbg char.
		String query = "INSERT INTO `mystic_grills`.`receipt` (`order_id`, `receipt_payment_date`, `receipt_payment_type`, `payment_amount`) VALUES (?, ?, ?, ?);";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, order.getOrderId());
			prep.setDate(2, receiptPaymentDate);
			prep.setString(3, receiptPaymentType);
			prep.setDouble(4, receiptPaymentAmount);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
	}
	
}
