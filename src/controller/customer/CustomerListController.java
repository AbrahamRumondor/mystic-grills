package controller.customer;

import controller.MGWindowController;
import controller.chefwaiter.ChefWaiterMenuController;
import controller.guest.GuestDefaultController;
import controller.model.UserController;
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
import model.User;
import view.customer.CustomerList;
import view.customer.CustomerMenuList;
import view.customer.CustomerOrderList;

public class CustomerListController {
	
	public static ActivityLog activityLog = ActivityLog.getInstance();
	private static CustomerList customerMenu = new CustomerList();

	static CustomerMenuList customerMenuList = new CustomerMenuList();
	static CustomerOrderList customerOrderList = new CustomerOrderList();
	
	public static void getDisplay(String option, Stage s, BorderPane borderPane, Label position) {
        if(option.equals("Menu")) {
        	CustomerListController.getCustomerMenuList(s, borderPane);
        } else if(option.equals("Order")) {
        	CustomerListController.getCustomerOrder(s, borderPane, position);
        }
	}
	
	public static void displayCustomerMenu(String option) {
		activityLog.getSceneStack().removeAllElements();
		MGWindow.getWindow().root.getChildren().clear();
		customerMenu.display(MGWindowController.getWindow().stage, option);
	}
	
	public static void addAction(Button allMenuBtn, Button home, Button viewOrderBtn, Stage s, Scene scene, BorderPane borderPane, Button logOutBtn) {
		allMenuBtn.setOnAction(
				e -> {
					displayCustomerMenu("Menu");;
				}	
		);
		
		viewOrderBtn.setOnAction(
				e -> {
					displayCustomerMenu("Order");
				}	
		);
		
	    home.setOnAction(
	    		e -> {
	    			// ini tidak masuk controller, karena pada dasarnya back hanya dimiliki oleh Main Screen.
	    			User user = UserController.getCurrentUser();
	            	CustomerDefaultController.goToCustomerDefault();
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
			
			addCustomerOrderAction( onGoingOrder, orderHistory,  position,  borderPane,  s);
		}
	
//	walaupun order, ini ditaro disini karena CustomerMenu bisa pilih untuk menampilkan menulist atau menampilkan MyOrder
	public static void addCustomerOrderAction(
			Button onGoingOrder,
			Button orderHistory,
			Label position,
			BorderPane borderPane,
			Stage s) 
	{
		onGoingOrder.setOnAction(
	    		e -> {
	    			position.setText("On-Going Order ");
	    			
	    			StackPane contents = customerOrderList.display(s);
	    			borderPane.setCenter(contents);
	    			CustomerListController.activityLog.add(contents);
	    		}	
	    ); 
		
		orderHistory.setOnAction(
	    		e -> {
	    			position.setText("Order History ");
	    			
	    			ChefWaiterMenuController.getChefOrderList(s, borderPane);
	    		}	
	    ); 
	}
	
//	configure view
	public static void setBorderpane(BorderPane borderPane, HBox headerBox) {
		borderPane.setTop(headerBox);
	}

	public static void setRootStackpane(StackPane root, BorderPane borderPane, Button home, HBox topButtonBox) {
		root.getChildren().addAll(borderPane, home, topButtonBox);
        root.setStyle("-fx-background-color: #f4f4f4;");
	}

	public static void createHeaderBox(Label userNameLbl, HBox headerBox) {
		headerBox.getChildren().addAll(userNameLbl);
		headerBox.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(userNameLbl, new Insets(0,0,0,80));
	}

	public static void createTopButtonBox(Label positionLbl, Button allMenuBtn, Button viewOrderBtn, Button logOutBtn,
			HBox topButtonBox) {
		topButtonBox.setMaxSize(450, 50);
		topButtonBox.setAlignment(Pos.TOP_RIGHT);
		topButtonBox.setSpacing(20);
		topButtonBox.getChildren().addAll(positionLbl, allMenuBtn, viewOrderBtn, logOutBtn);
	}

	public static void setLabelFont(Label userNameLbl, Label positionLbl) {
		userNameLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
		positionLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
	}

	public static void setStackpane(BorderPane borderPane, HBox topButtonBox, Button home) {
		StackPane.setMargin(topButtonBox, new Insets(12,10,10,10));
        StackPane.setAlignment(topButtonBox, Pos.TOP_RIGHT);
		StackPane.setMargin(borderPane, new Insets(10,10,10,10));
        StackPane.setMargin(home, new Insets(12,10,10,10));
        StackPane.setAlignment(home, Pos.TOP_LEFT);
	}
	
	public static void showCustomerList(Scene scene, Stage s) {
		s.setScene(scene);
		s.setTitle("Mystic Grills");
		s.show();
	}

	
}
