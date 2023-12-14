package controller.cashier;

import controller.MGWindowController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import view.MGWindow;
import controller.customer.CustomerMenuController;
import view.admin.AdminDefault;
import view.popup.menuitem.MenuItemPopup;
import view.cashier.CashierDefault;

public class CashierDefaultController {
	
	private static CashierDefault cashierDefault = new CashierDefault();
	
	public static void addAction(
			Button viewOrder,
			Button viewReceipt,
			Stage s,
			Scene scene,
			BorderPane borderPane) 
	{
		viewOrder.setOnAction(
				e -> {	
					CashierListController.displayOrderMenu("Order");
				}	
		);
		
		viewReceipt.setOnAction(
				e -> {
					CashierListController.displayOrderMenu("Receipt");
				}	
		);
	}

	public static void goToCashierDefault() {
	//		remove stack, remove isi stackpane
			MGWindowController.activityLog.getSceneStack().removeAllElements();
			MGWindow.getWindow().root.getChildren().clear();

			cashierDefault.display(MGWindow.getWindow().stage);
		}
	
}
