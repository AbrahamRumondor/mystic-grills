package view.chefwaiter;

import controller.chefwaiter.ChefWaiterOrderDetailListController;
import controller.model.OrderController;
import controller.model.UserController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.MenuItem;
import model.Order;
import model.OrderItem;


public class ChefWaiterOrderDetailList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, proceedBtn;
	VBox namePane, passwordPane, idPane, proceedPane, headerPane;
	Label orderIdLbl, userNameLbl, orderDateLbl, orderStatusLbl, totalLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	HBox buttonPane;
	
	Integer orderId;
//	User orderUser;
//	Date orderDate;
//	String orderStatus;
	
	MenuItem currentMenu;
	
	private void makeTable(Order order, Stage s, BorderPane borderPane, boolean isCustomer) {
		table = new TableView<>();
		
		TableColumn<OrderItem, String> menuNameColumn = new TableColumn<>("Menu Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
		TableColumn<OrderItem, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn<OrderItem, Double> totalColumn = new TableColumn<>("Sub-Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		table.getColumns().addAll(menuNameColumn, priceColumn, quantityColumn, totalColumn);

		ObservableList<OrderItem> items = ChefWaiterOrderDetailListController.getAllData(order);
		table.setItems(items);
		
		OrderController.setOrder(order);
		
		assignTableItemToLocal(order, s, borderPane, isCustomer);
		ChefWaiterOrderDetailListController.getButtonAction(
				order, 
				s, 
				borderPane, 
				isCustomer,
				proceedBtn,
				addBtn,
				updBtn,
				deleteBtn,
				table,
				currentMenu);
	}
	
	void headerPane(Order order) {
		 orderIdLbl = new Label("Order Id: " + order.getOrderId());
		 userNameLbl = new Label("Order Client: " + order.getOrderUser().getUserName());
		 orderDateLbl =  new Label("Order Date: " + order.getOrderDateString());
		 orderStatusLbl = new Label("Order Status: " + order.getOrderStatus());
		 
		 ChefWaiterOrderDetailListController.setLabelFont(
				 orderIdLbl,
				 userNameLbl,
				 orderDateLbl,
				 orderStatusLbl);
		 headerPane = ChefWaiterOrderDetailListController.defineHeaderPane(
				headerPane,
				orderIdLbl, 
				userNameLbl, 
				orderDateLbl,
				orderStatusLbl);
	}
	
	void makeForm(Order order){
		addBtn = new Button("Add New Menu");
		updBtn = new Button("Update Order Menu");
		deleteBtn = new Button("Delete Order Menu");

		buttonPane = new HBox();
		buttonPane.getChildren().addAll(addBtn, updBtn, deleteBtn);
		buttonPane.setSpacing(5);
	}
	
	void makeSubmitPane(Order order){
		User user = UserController.getCurrentUser();
		
		String proceedBtnName = new String();
		proceedBtnName = ChefWaiterOrderDetailListController.getProceedBtnName(user, proceedBtnName);
		
		proceedBtn = new Button(proceedBtnName);
		proceedPane = ChefWaiterOrderDetailListController.defineProceedPane(proceedPane, proceedBtn);
	}
	
	void makeBackBtn(Order order){
		proceedBtn = new Button("back");
		buttonPane = ChefWaiterOrderDetailListController.defineButtonPane(buttonPane, proceedBtn);
	}
	
	public StackPane display(Stage s, Order order, BorderPane borderPane) {
		User user = UserController.getCurrentUser();
		boolean isCustomer = user.getUserRole().equals("Customer");
		
		getForm(order, isCustomer);
		headerPane(order);
		getSubmitPane(order, isCustomer);
		makeTable(order, s, borderPane, isCustomer);
		
		VBox pagePane = new VBox(10);
		ChefWaiterOrderDetailListController.definePagePane(isCustomer, pagePane, headerPane, buttonPane, table, proceedPane);

		root = ChefWaiterOrderDetailListController.setRootStackpane(pagePane, root);
		
		ChefWaiterOrderDetailListController.refreshTableView(table, order);
		return root;
	}

	private void getSubmitPane(Order order, boolean isCustomer) {
		if(!isCustomer) {
			makeSubmitPane(order);
		}
	}

	private void getForm(Order order, boolean isCustomer) {
		if(!isCustomer) {
			makeForm(order);
		} else {
			makeBackBtn(order);
		}
	}

	private void assignTableItemToLocal(Order order, Stage s, BorderPane borderPane, boolean isCustomer) {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentMenu = newValue.getMenuItem();
				orderId = newValue.getOrderId();
				
				ChefWaiterOrderDetailListController.getButtonAction(
						order, 
						s, 
						borderPane, 
						isCustomer,
						proceedBtn,
						addBtn,
						updBtn,
						deleteBtn,
						table,
						currentMenu);
			}
		});
	}
	
}

