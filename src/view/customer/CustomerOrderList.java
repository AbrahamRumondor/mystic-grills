package view.customer;

import controller.customer.CustomerOrderListController;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MenuItem;
import model.OrderItem;


public class CustomerOrderList {
	public static StackPane root;
		
	public static TableView<OrderItem> table;
	Button updBtn, addBtn, deleteBtn, submitBtn;
	VBox formPane, namePane, passwordPane, idPane, submitPane;
	Label nameLbl, descriptionLbl, priceLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	String orderQty, orderName, menuPrice;
	
	MenuItem currentItem;
	
	private void makeTable() {
		table = new TableView<>();

		TableColumn<OrderItem, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity" ));
		TableColumn<OrderItem, String> menuNameColumn = new TableColumn<>("Menu Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
		TableColumn<OrderItem, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		ObservableList<OrderItem> items = CustomerOrderListController.getAllData();
		table.getColumns().addAll(quantityColumn, menuNameColumn, priceColumn);
		CustomerOrderListController.defineOrderItemTable(items, table);

		assignTableItemToLocal();
		CustomerOrderListController.addAction(orderQty, updBtn, deleteBtn, submitBtn, addBtn, currentItem, table);
	}

	void makeForm(){
		addBtn = new Button("Add New Menu");
		updBtn = new Button("Update Order Menu");
		deleteBtn = new Button("Delete Order Menu");

		HBox buttonPane = new HBox();
		CustomerOrderListController.createButtonPane(buttonPane, addBtn, updBtn, deleteBtn);
		
		formPane = new VBox(10);
		CustomerOrderListController.createFormBox(buttonPane, formPane);
	}

	void makeSubmitPane(){
		submitBtn = new Button("Submit Order");
		
		submitPane = new VBox();
		CustomerOrderListController.createSubmitPane(submitPane, submitBtn);
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeSubmitPane();
		makeTable();

		VBox pagePane = new VBox(10);
		CustomerOrderListController.createPagePane(pagePane,formPane, table, submitPane);

		root = CustomerOrderListController.createRootStackpane(pagePane, root);
		
		CustomerOrderListController.refreshTableView(table);
		
		return root;
	}
	
	private void assignTableItemToLocal() {
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentItem = newValue.getMenuItem();
				orderQty = newValue.getQuantity().toString();
				orderName = newValue.getMenuItem().getMenuItemName();
				menuPrice = String.valueOf(newValue.getMenuItem().getMenuItemPrice() * newValue.getQuantity());
				
				CustomerOrderListController.addAction(orderQty, updBtn, deleteBtn, submitBtn, addBtn, currentItem, table);
			}
		});
	}
}
