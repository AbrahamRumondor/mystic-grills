//package view;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.sql.PreparedStatement;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.User;
//import model.MenuItem;
//import model.Connect;
//
//
//public class BuatAdminSementara {
//	public static StackPane root;
//
//	TableView<MenuItem> table;
//	Button addBtn, updateBtn, deleteBtn;
//	VBox form, namePane, passwordPane, idPane;
//	Label nameLbl, descriptionLbl, priceLbl;
//	TextField nameTxt, descriptionTxt, priceTxt;
//
//	private void makeTable() {
//		table = new TableView<>();
//
//		table = new TableView<>();
//		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
//		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
//		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
//		TableColumn<MenuItem, String> priceColumn = new TableColumn<>("Price");
//		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
//		
//		table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn);
//
//		ObservableList<MenuItem> items = getAllData();
//		table.setItems(items);
//
//		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//			if (newValue != null) {
//				System.out.println(newValue.getMenuItemName() + " is " + newValue.getMenuItemId() +" months old");
//				nameTxt.setText(newValue.getMenuItemName());
//				descriptionTxt.setText(newValue.getMenuItemDescription());
//				priceTxt.setText(newValue.getMenuItemPrice().toString());
//			}
//		});
//		
//		addAction();
//	}
//
//	void makeForm(){
//
//		nameLbl = new Label("Menu Name");
//		nameTxt = new TextField();
//
//		descriptionLbl = new Label("Menu Description");
//		descriptionTxt = new TextField();
//		
//		priceLbl = new Label("Menu Price");
//		priceTxt = new TextField();
////		priceTxt.setDisable(true);
//
//		addBtn = new Button("Add");
//		updateBtn = new Button("Update");
//		deleteBtn = new Button("Delete");
//
//		idPane = new VBox();
//		idPane.getChildren().addAll(priceLbl, priceTxt);
//
//		namePane = new VBox();
//		namePane.getChildren().addAll(nameLbl, nameTxt);
//
//		passwordPane = new VBox();
//		passwordPane.getChildren().addAll(descriptionLbl, descriptionTxt);
//
//		HBox buttonPane = new HBox();
//		buttonPane.getChildren().addAll(addBtn, updateBtn, deleteBtn);
//		buttonPane.setSpacing(5);
//
//		VBox inputField = new VBox(5);
//		inputField.getChildren().addAll(idPane, namePane, passwordPane);
//
//		form = new VBox(10);
//		form.getChildren().addAll(inputField, buttonPane);
//
//
//		form.setStyle("-fx-background-color: white");
//	}
//
//	void addAction(){
//
//
//		addBtn.setOnAction(e ->{
//			String name = nameTxt.getText();
//			String description = descriptionTxt.getText();
//			String price = priceTxt.getText();
//			
//			MenuItem.createMenuItem(name, description, price);
//			
//			refreshTableView();
//		});
//		
//		updateBtn.setOnAction(e->{
//			String name = nameTxt.getText();
//			int id = Integer.parseInt(priceTxt.getText());
//			String password = descriptionTxt.getText();
//			String query = "UPDATE Users SET user_name = ?, user_password = ? WHERE user_id = ? ";
//			
//			PreparedStatement prep = Connect.getConnection().prepare(query);
//			try {
//				prep.setString(1, name);
//				prep.setString(2, password);
//				prep.setInt(3, id);
//				Connect.getConnection().executePreparedUpdate(prep);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			refreshTableView();
//		});
//		
//		deleteBtn.setOnAction(e->{
//			int id = Integer.parseInt(priceTxt.getText());
//			String query = "DELETE FROM users where user_id = ?";
//			PreparedStatement prep = Connect.getConnection().prepare(query);
//			try {
//				prep.setInt(1,  id);
//				Connect.getConnection().executePreparedUpdate(prep);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			refreshTableView();
//		});
//	}
//
//	private void refreshTableView() {
//		ObservableList<MenuItem> items = FXCollections.observableArrayList();
//		table.setItems(items);
//	}
//
//	private ObservableList<MenuItem> getAllData() {
//		ObservableList<MenuItem> items = FXCollections.observableArrayList();
//		
//		ArrayList<MenuItem> arrayItems = MenuItem.getAllUsers();
//		
//		for (MenuItem menuItem : arrayItems) {
//			items.add(menuItem);
//		}
//		
//		return items;
//
//	}
//	
//	public StackPane display(Stage s) {
//		makeForm();
//		makeTable();
//
//		VBox page = new VBox(10);
//		page.getChildren().addAll(form, table);
//		page.setPadding(new Insets(10));
//
//		root = new StackPane();
////		Scene scene = new Scene(root, 400, 400);
//		root.getChildren().add(page);
//		
//		return root;
//	}
//
//
//}
//













//// INI YANG CUSTOMER MENUNYA
//package view.customer;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.sql.PreparedStatement;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.User;
//import model.MenuItem;
//import model.Connect;
//
//
//public class CustomerMenuList {
//	public static StackPane root;
//
//	TableView<MenuItem> table;
//	Button addBtn, updateBtn, deleteBtn;
//	VBox form, namePane, passwordPane, idPane;
//	Label nameLbl, descriptionLbl, priceLbl;
//	TextField nameTxt, descriptionTxt, priceTxt;
//
//	private void makeTable() {
//		table = new TableView<>();
//
//		table = new TableView<>();
//		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
//		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
//		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
//		TableColumn<MenuItem, String> priceColumn = new TableColumn<>("Price");
//		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
//		
//		table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn);
//
//		ObservableList<MenuItem> items = getAllData();
//		table.setItems(items);
//
//		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//			if (newValue != null) {
//				System.out.println(newValue.getMenuItemName() + " is " + newValue.getMenuItemId() +" months old");
//				nameTxt.setText(newValue.getMenuItemName());
//				descriptionTxt.setText(newValue.getMenuItemDescription());
//				priceTxt.setText(newValue.getMenuItemPrice().toString());
//			}
//		});
//		
//		addAction();
//	}
//
//	void makeForm(){
//
//		nameLbl = new Label("Menu Name");
//		nameTxt = new TextField();
//
//		descriptionLbl = new Label("Menu Description");
//		descriptionTxt = new TextField();
//		
//		priceLbl = new Label("Menu Price");
//		priceTxt = new TextField();
////		priceTxt.setDisable(true);
//
//		addBtn = new Button("Add");
//		updateBtn = new Button("Update");
//		deleteBtn = new Button("Delete");
//
//		idPane = new VBox();
//		idPane.getChildren().addAll(priceLbl, priceTxt);
//
//		namePane = new VBox();
//		namePane.getChildren().addAll(nameLbl, nameTxt);
//
//		passwordPane = new VBox();
//		passwordPane.getChildren().addAll(descriptionLbl, descriptionTxt);
//
//		HBox buttonPane = new HBox();
//		buttonPane.getChildren().addAll(addBtn, updateBtn, deleteBtn);
//		buttonPane.setSpacing(5);
//
//		VBox inputField = new VBox(5);
//		inputField.getChildren().addAll(idPane, namePane, passwordPane);
//
//		form = new VBox(10);
//		form.getChildren().addAll(inputField, buttonPane);
//
//
//		form.setStyle("-fx-background-color: white");
//	}
//
//	void addAction(){
//
//
//		addBtn.setOnAction(e ->{
//			String name = nameTxt.getText();
//			String description = descriptionTxt.getText();
//			String price = priceTxt.getText();
//			
//			MenuItem.createMenuItem(name, description, price);
//			
//			refreshTableView();
//		});
//		
//		updateBtn.setOnAction(e->{
//			String name = nameTxt.getText();
//			int id = Integer.parseInt(priceTxt.getText());
//			String password = descriptionTxt.getText();
//			String query = "UPDATE Users SET user_name = ?, user_password = ? WHERE user_id = ? ";
//			
//			PreparedStatement prep = Connect.getConnection().prepare(query);
//			try {
//				prep.setString(1, name);
//				prep.setString(2, password);
//				prep.setInt(3, id);
//				Connect.getConnection().executePreparedUpdate(prep);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			refreshTableView();
//		});
//		
//		deleteBtn.setOnAction(e->{
//			int id = Integer.parseInt(priceTxt.getText());
//			String query = "DELETE FROM users where user_id = ?";
//			PreparedStatement prep = Connect.getConnection().prepare(query);
//			try {
//				prep.setInt(1,  id);
//				Connect.getConnection().executePreparedUpdate(prep);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			refreshTableView();
//		});
//	}
//
//	private void refreshTableView() {
//		ObservableList<MenuItem> items = FXCollections.observableArrayList();
//		table.setItems(items);
//	}
//
//	private ObservableList<MenuItem> getAllData() {
//		ObservableList<MenuItem> items = FXCollections.observableArrayList();
//		
//		ArrayList<MenuItem> arrayItems = MenuItem.getAllUsers();
//		
//		for (MenuItem menuItem : arrayItems) {
//			items.add(menuItem);
//		}
//		
//		return items;
//
//	}
//	
//	public StackPane display(Stage s) {
//		makeForm();
//		makeTable();
//
//		VBox page = new VBox(10);
//		page.getChildren().addAll(form, table);
//		page.setPadding(new Insets(10));
//
//		root = new StackPane();
////		Scene scene = new Scene(root, 400, 400);
//		root.getChildren().add(page);
//		
//		return root;
//	}
//
//
//}

