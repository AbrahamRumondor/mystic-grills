package view.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.customer.CustomerMenuListController;

import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import model.User;
import view.AddMenu;
import model.MenuItem;
import model.Connect;


public class CustomerMenuList {
	public static StackPane root;

	TableView<MenuItem> table;
	Button addBtn, updateBtn, deleteBtn;
	VBox form, namePane, passwordPane, idPane;
	Label nameLbl, descriptionLbl, priceLbl;
	TextField nameTxt, descriptionTxt, priceTxt;
	
	String menuName, menuDescription, menuPrice;
	
	MenuItem currentItem;
	
	private void makeTable() {
		table = new TableView<>();

		table = new TableView<>();
		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName" ));
		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription" ));
		TableColumn<MenuItem, String> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		
		table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn);

		ObservableList<MenuItem> items = CustomerMenuListController.getAllData();
		table.setItems(items);

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				currentItem = newValue;
				System.out.println(newValue.getMenuItemName() + " is " + newValue.getMenuItemId() +" months old");
				menuName = newValue.getMenuItemName();
				menuDescription = newValue.getMenuItemDescription();
				menuPrice = newValue.getMenuItemPrice().toString();
				
				CustomerMenuListController.addAction(menuName, addBtn, currentItem,table);
			}
		});
		
	
		CustomerMenuListController.addAction(menuName, addBtn, currentItem, table);
	}

	void makeForm(){
		addBtn = new Button("Add");

		HBox buttonPane = new HBox();
		buttonPane.getChildren().addAll(addBtn);
		buttonPane.setSpacing(5);
		form = new VBox(10);
		form.getChildren().addAll(buttonPane);
	}
	
	public StackPane display(Stage s) {
		makeForm();
		makeTable();

		VBox page = new VBox(10);
		page.getChildren().addAll(form, table);
		page.setPadding(new Insets(10));

		root = new StackPane();
//		Scene scene = new Scene(root, 400, 400);
		root.getChildren().add(page);
		
		return root;
	}


}
