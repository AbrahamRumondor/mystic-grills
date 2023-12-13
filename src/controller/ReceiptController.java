package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Connect;
import model.Order;
import model.Receipt;

public class ReceiptController {
	
	public static boolean createReceipt(
			Order order,
			String receiptPaymentType,
			Double receiptPaymentAmount,
			Date receiptPaymentDate
			)
	{
		return Receipt.createReceipt(order, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate);
	}
	
}
