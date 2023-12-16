package controller.model;

import java.sql.Date;
import java.util.ArrayList;
import model.Order;
import model.Receipt;

public class ReceiptController {
//	controller model
	public static boolean createReceipt(
			Order order,
			String receiptPaymentType,
			Double receiptPaymentAmount,
			Date receiptPaymentDate
			)
	{
		return Receipt.createReceipt(order, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate);
	}
	
	public static void updateReceipt(
			Integer orderId,
			String receiptPaymentType,
			Double receiptPaymentAmount,
			Date receiptPaymentDate
			) 
	{		
		Receipt.updateReceipt(orderId, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate);
	}
	
	public static void deleteReceipt(Integer orderId) {
		Receipt.deleteReceipt(orderId);
	}
	
	public static Receipt getReceiptById(Integer receiptId) {
		return Receipt.getReceiptById(receiptId);
	}
	
	public static ArrayList<Receipt> getAllReceipts() {
		return Receipt.getAllReceipts();
	}
}
