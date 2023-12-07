package view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Connect;

public class MGWindow {
	
/*
 * 		 NOTE: 
 * 		 class ini merupakan canvas dari aplikasi/ base display dr aplikasi.
 * 		 karena sistem aplikasi tidak timpa keseluruhan screen (melainkan hanya ganti yang perlu di ganti)
 * 		 sehingga dibuat class ini karena base root, scene, stage nya biasa dipake oleh file lain.
 * 		 Oleh karena itu juga class ini ada controllernya.
 * 
 * */
	
	public StackPane root;
	public Scene scene;
	public Stage stage;
	
	public MGWindow(Stage s) {
		root = new StackPane();
		scene = new Scene(root, 960, 600);
		stage = s;
	}
	
	private static MGWindow mgwindow;
	
//	mastiin jgn sampe window kita jadi ada banyak.
	public static MGWindow setWindow(Stage s) {
		if(mgwindow == null) {
			synchronized (MGWindow.class) {
				if(mgwindow==null)mgwindow = new MGWindow(s);
			}
		}
		return mgwindow;
	}
	
	public static MGWindow getWindow() {
		return mgwindow;
	}


	
	
	
}
