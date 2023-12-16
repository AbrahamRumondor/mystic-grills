package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.OrderController;
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
	
	public static ArrayList<Receipt> getAllReceipts() {
		ArrayList<Receipt> receipts = new ArrayList<>();
		
		String query = "SELECT * FROM receipt";
		
		
		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
			while(rs.next()) {
				Integer receiptId = Integer.valueOf(rs.getString("receipt_id"));
				Integer orderId = Integer.valueOf(rs.getString("order_id"));
				Date date = rs.getDate("receipt_payment_date");
				String type = rs.getString("receipt_payment_type");
				Double amount = rs.getDouble("payment_amount");
				
				Order order = OrderController.getOrderByOrderId(orderId);
				
				receipts.add(new Receipt(receiptId, order, amount, date, type));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receipts;
	}
	
	public static Receipt getReceiptById(Integer receiptId) {
		ArrayList<Receipt> receipts = getAllReceipts();
		
		for(Receipt receipt : receipts) {
			if(receipt.getReceiptId() == receiptId)
				return receipt;
		}
		return null;
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
	
	public static void updateReceipt(
			Integer orderId,
			String receiptPaymentType,
			Double receiptPaymentAmount,
			Date receiptPaymentDate
			) 
	{		
		String query = "UPDATE `mystic_grills`.`receipt` SET `receipt_payment_date` = ?, `receipt_payment_type` = ?, `payment_amount` = ? WHERE (`order_id` = ?);";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setDate(1, receiptPaymentDate);
			prep.setString(2, receiptPaymentType);
			prep.setDouble(3, receiptPaymentAmount);
			prep.setInt(4, orderId);
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void deleteReceipt(Integer orderId) {
		String query = "DELETE FROM `mystic_grills`.`receipt` WHERE (`order_id` = ?);";
		
		PreparedStatement prep = Connect.getConnection().prepare(query);
		try {
			prep.setInt(1, orderId);;
			Connect.getConnection().executePreparedUpdate(prep);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
//	getter setter
	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Order getReceiptOrder() {
		return receiptOrder;
	}

	public void setReceiptOrder(Order receiptOrder) {
		this.receiptOrder = receiptOrder;
	}

	public Double getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}

	public void setReceiptPaymentAmount(Double receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}

	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}

	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}

	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}

	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
	
	
}
