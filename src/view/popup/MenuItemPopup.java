package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
import controller.popup.MenuItemPopupController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MGWindow;
import model.MenuItem;

public class MenuItemPopup {
		
	static VBox namePane, headerPane, addItemPane;
	static HBox buttonPane;
	static Label nameLbl, descLbl, priceLbl, fillLbl;
	static TextField nameTxt, descTxt, priceTxt;
	
	public static StackPane show(Button btn, Button updateBtn, Button deleteBtn, MenuItem chosenMenu, String action) {		
		
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label(action + " Menu");
		MenuItemPopupController.setLabelFont(addPopup);
		headerPane = MenuItemPopupController.defineHeaderPane(addPopup, headerPane);
		
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane = MenuItemPopupController.defineButtonPane(cancelBtn, confirmBtn, buttonPane);
		
		nameLbl = new Label("Menu name :");
		nameTxt = new TextField();
		
		descLbl = new Label("Menu description :");
		descTxt = new TextField();
		
		priceLbl = new Label("Menu price :");
		priceTxt = new TextField();
		
		addItemPane = MenuItemPopupController.defineAddItemPane(
				addItemPane,
						nameLbl, nameTxt,
						descLbl, descTxt,
						priceLbl, priceTxt);
		MenuItemPopupController.getAction(btn, updateBtn, deleteBtn, chosenMenu, action, confirmBtn, nameTxt, descTxt, priceTxt);
		MenuItemPopupController.addButtonAction(btn, window, cancelBtn);
		
		MenuItemPopupController.configureBorderpane(root, headerPane, addItemPane, buttonPane);
		
		StackPane container = new StackPane(root);
		MenuItemPopupController.defineContainer(window, container);
		
		return container;
	}
}
