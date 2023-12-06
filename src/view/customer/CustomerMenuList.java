//package view.customer;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
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
//import model.Connect;
//
//
//public class CustomerMenuList {
//	public static StackPane root;
//
//	TableView<User> table;
//	Button addBtn, updateBtn, deleteBtn;
//	VBox form, namePane, passwordPane, idPane;
//	Label nameLbl, passwordLbl, idLbl;
//	TextField nameTxt, passwordTxt, idTxt;
//
//	private void makeTable() {
//		table = new TableView<>();
//
//		table = new TableView<>();
//		TableColumn<User, Integer> idColumn = new TableColumn<>("Id");
//		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//		TableColumn<User, String> nameColumn = new TableColumn<>("Name");
//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name" ));
//		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
//		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
//		
//		table.getColumns().addAll(idColumn, nameColumn, passwordColumn);
//
//		ObservableList<User> Users = getAllData();
//		table.setItems(Users);
//
//		table.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//			if (newValue != null) {
//				System.out.println(newValue.getName() + " is " + newValue.getPassword() +" months old");
//				nameTxt.setText(newValue.getName());
//				passwordTxt.setText(newValue.getPassword());
//				idTxt.setText(newValue.getId().toString());
//			}
//		});
//		
//		addAction();
//	}
//
//	void makeForm(){
//		idLbl = new Label("Id");
//		idTxt = new TextField();
//		idTxt.setDisable(true);
//
//		nameLbl = new Label("Name");
//		nameTxt = new TextField();
//
//		passwordLbl = new Label("Password");
//		passwordTxt = new TextField();
//
//		addBtn = new Button("Add");
//		updateBtn = new Button("Update");
//		deleteBtn = new Button("Delete");
//
//		idPane = new VBox();
//		idPane.getChildren().addAll(idLbl, idTxt);
//
//		namePane = new VBox();
//		namePane.getChildren().addAll(nameLbl, nameTxt);
//
//		passwordPane = new VBox();
//		passwordPane.getChildren().addAll(passwordLbl, passwordTxt);
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
//			String password = passwordTxt.getText();
//			
//			User newUser = new User(name, password);
//			newUser.insert();
//			
//			refreshTableView();
//		});
//		
//		updateBtn.setOnAction(e->{
//			String name = nameTxt.getText();
//			int id = Integer.parseInt(idTxt.getText());
//			String password = passwordTxt.getText();
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
//			int id = Integer.parseInt(idTxt.getText());
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
//		ObservableList<User> Users = getAllData();
//		table.setItems(Users);
//	}
//
//	private ObservableList<User> getAllData() {
//		ObservableList<User> Users = FXCollections.observableArrayList();
//		String query = "SELECT * FROM Users";
//
//		try (ResultSet rs = Connect.getConnection().executeStatementQuery(query)) {
//			while (rs.next()) {
//				User User = new User(rs.getString("user_name"), rs.getString("user_password")).setId(rs.getInt("user_id"));
//				
//				Users.add(User);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Users;
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
