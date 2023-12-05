package view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Connect;

public class MGWindow {
	
/*
 * 		 NOTE: 
 * 		 class ini tidak dimasukkan ke dalam controller
 * 		 karena MGWindow pada dasarnya adalah canvas/screen base dari aplikasi ini.
 * 		 Oleh karena itu MGWindow dipakai disemua view yang membuatnya
 * 		 tidak dibatasi oleh controller apapun.
 * 
 * */
	
	public StackPane root;
	public Scene scene;
	public Stage stage;
	
	public MGWindow(Stage s) {
		root = new StackPane();
		scene = new Scene(root, 500, 500);
		stage = s;
	}
	
	private static MGWindow mgwindow;
	
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
