package controller.customer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ActivityLog;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;

public class CustomerController {
	
	private static CustomerMenuList customerMenuList = new CustomerMenuList();
	private static ActivityLog activityLog = ActivityLog.getInstance();
	private static CustomerOrderList customerOrderList = new CustomerOrderList();
	
	public static void getCustomerMenuList(Stage s, BorderPane borderPane) {
		StackPane contents = customerMenuList.display(s);
//		DISINIII
		borderPane.setCenter(contents);
		
		activityLog.add(contents);
	}
	
	public static void getCustomerOrder(Stage s, BorderPane borderPane, Label position) {
		
		position.setText("My Order ");
		
		Button onGoingOrder = new Button("On-going Order");
		Button orderHistory = new Button("Order History");
		
//		 untuk login dan signup
		HBox tengah = new HBox();
		tengah.setAlignment(Pos.CENTER);
		tengah.getChildren().addAll(onGoingOrder, orderHistory);
		tengah.setSpacing(100);
		
		borderPane.setCenter(tengah);
		
		activityLog.add(tengah);
		
		addCustomerOrderAction( onGoingOrder,  position,  borderPane,  s);
	}
	
	public static void addCustomerOrderAction(Button onGoingOrder, Label position, BorderPane borderPane, Stage s) {
		onGoingOrder.setOnAction(
        		e -> {
        			position.setText("On-Going Order ");
        			
        			StackPane contents = customerOrderList.display(s);
        			
        			borderPane.setCenter(contents);
        			
        			activityLog.add(contents);
        		}	
        ); 
	}
	
}
