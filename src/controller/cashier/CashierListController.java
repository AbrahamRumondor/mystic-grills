package controller.cashier;

import controller.MGWindowController;
import controller.guest.GuestDefaultController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ActivityLog;
import model.MGWindow;
import model.Order;
import model.User;
import view.customer.CustomerList;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;
import view.admin.*;
import view.cashier.CashierOrderList;
import view.cashier.CashierReceiptList;
import view.cashier.CashierList;

public class CashierListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static CashierList cashierList = new CashierList();
	
	
//	static AdminMenuList adminMenuList = new AdminMenuList();
	static CashierOrderList cashierOrderList = new CashierOrderList();
	static CashierReceiptList cashierReceiptList = new CashierReceiptList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Order")) {
        	getCashierOrderList(s, borderPane, position);
        } else if(option.equals("Receipt")) {
        	getCashierReceiptList(s, borderPane, position);
        }
	}
	
	public static void displayOrderMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		cashierList.display(MGWindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button orderBtn, Button home, Button receiptBtn, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
		orderBtn.setOnAction(
				e -> {
					displayOrderMenu("Order");;
				}	
		);
		
		receiptBtn.setOnAction(
				e -> {
					displayOrderMenu("Receipt");
				}	
		);
		
	    home.setOnAction(
	    		e -> {
	    			
	            	CashierDefaultController.goToCashierDefault();
	    		}	
	    ); 
	    
	    logOutBtn.setOnAction(
	    		e -> {
	    			MGWindowController.activityLog.getSceneStack().removeAllElements();
	    			MGWindow.getWindow().root.getChildren().clear();
	    			
	    			GuestDefaultController.displayGuestDefault(s);
	    		}	
	    ); 
	    
	}

	public static void getCashierOrderList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All Order ");
		
			StackPane contents = cashierOrderList.display(s, borderPane);
			borderPane.setCenter(contents);
			activityLog.add(contents);
		}

	public static void getCashierReceiptList(Stage s, BorderPane borderPane, Label position) {
			position.setText("All Receipt ");
			
			StackPane contents = cashierReceiptList.display(s, borderPane);
			borderPane.setCenter(contents);
			activityLog.add(contents);
		}
	
//	configure view
	public static void setRootStackpane(StackPane root, BorderPane borderPane, HBox topButtonPane, Button home) {
		root.getChildren().addAll(borderPane, home, topButtonPane);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	public static void configureBorderpane(BorderPane borderPane, HBox headerPane) {
		borderPane.setTop(headerPane);
	}

	public static void createTopButtonPane(Label position, Button viewOrderBtn, Button viewReceiptBtn, Button logOutBtn,
			HBox topButtonPane) {
		topButtonPane.setMaxSize(450, 50);
		topButtonPane.setAlignment(Pos.TOP_RIGHT);
		topButtonPane.setSpacing(20);
		topButtonPane.getChildren().addAll(position, viewOrderBtn, viewReceiptBtn, logOutBtn);
	}

	public static void createHeaderPane(Label userNameLbl, HBox headerPane) {
		headerPane.getChildren().addAll(userNameLbl);
		headerPane.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
	}

	public static void setLabelFont(Label userNameLbl, Label position) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		position.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	public static void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void showCashierList(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}
}

