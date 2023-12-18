package view.popup;

import javafx.scene.layout.BorderPane;
import java.sql.Date;
import java.time.LocalDateTime;
import controller.MGWindowController;
import controller.cashier.CashierOrderListController;
import controller.model.OrderController;
import controller.model.ReceiptController;
import controller.popup.ProceedOrderPopupController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ActivityLog;
import model.MGWindow;
import model.Order;

public class ProceedOrderPopup {
	
	static VBox namePane, headerPane, rolePane;
	static HBox buttonPane;
	static Label nameLbl, descriptionLbl, typeLbl, fillLbl, paymentLbl;
	static TextField nameTxt, descriptionTxt, typeTxt, paymentTxt;
	
	public static StackPane show(
			Order order,
			Button btn,
			String position) {
		
		MGWindow window = MGWindowController.getWindow();
		BorderPane root = new BorderPane();
		
		Label addPopup = new Label("Proceed Order");
		Label content = new Label("Total Payment: " + order.getOrderTotalString());
		ProceedOrderPopupController.setLabelFont(addPopup, content);
		
		Label spacing = new Label("");
		
		headerPane = ProceedOrderPopupController.defineHeaderPane(addPopup, content, spacing, headerPane);
		
		Button cancelBtn = new Button("Cancel");
		Button confirmBtn = new Button("Confirm");
		buttonPane = ProceedOrderPopupController.defineButtonPane(cancelBtn, confirmBtn, buttonPane);
		
		typeTxt = new TextField();
		typeLbl = new Label("Payment Type :");
		fillLbl = new Label("[‘Cash’, ‘Debit’, ‘Credit’]");
			
		paymentLbl = new Label("Payment amount :");
		paymentTxt = new TextField("");
		
		rolePane = ProceedOrderPopupController.defineRolePane(
				rolePane,
				typeLbl,
				fillLbl,
				typeTxt,
				paymentLbl,
				paymentTxt);
		
		ProceedOrderPopupController.addButtonAction(order, btn, position, window, cancelBtn, confirmBtn, typeTxt, paymentTxt);
		
		ProceedOrderPopupController.configureBorderpane(root, headerPane, rolePane, buttonPane);
		
		StackPane container = new StackPane(root);
		ProceedOrderPopupController.setContainer(window, container);
		return container;
	}
}

