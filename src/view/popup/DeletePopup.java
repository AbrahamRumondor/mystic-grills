package view.popup;

import javafx.scene.layout.BorderPane;
import controller.MGWindowController;
import controller.model.UserController;
import controller.popup.DeletePopupController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.MGWindow;
import model.User;

public class DeletePopup {
		
	static VBox namePane, headerPane, quantityPane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, quantityLbl;
	static TextField nameTxt, descriptionTxt, quantityTxt;
	
	static User user;
	
	public static StackPane show(Integer id, Button deleteBtn, Button updateBtn, String target, Button addBtn) {
		
		user = UserController.getUserById(id);
		
		MGWindow window = MGWindowController.getWindow();
		
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Delete " + target);
		Label deleteMsg = new Label("Are you sure you want to delete ");
		Label content = new Label();
		DeletePopupController.setLabelFont(addPopup, deleteMsg, content);
		
		DeletePopupController.setContent(id, content, target);
		
		headerPane = DeletePopupController.defineHeaderPane(addPopup, deleteMsg, content, headerPane);
	
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane = DeletePopupController.defineButtonPane(cancelBtn, confirmBtn, buttonPane);
		
		DeletePopupController.setDeleteConfirmBtn(id, confirmBtn, updateBtn, target, addBtn);

		DeletePopupController.addOnCancelAction(target, addBtn, window, cancelBtn);
		DeletePopupController.configureBorderpane(root, headerPane, quantityPane, buttonPane);
		
		StackPane container = new StackPane(root);
		DeletePopupController.setContainer(window, container);
		return container;
	}
}
