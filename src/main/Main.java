package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.guest.GuestDefault; 

public class Main extends Application{

	// NOTE: MOHON UNTUK MENGGUNAKAN PACKAGE PRESENTATION VERSI HIERARCHICAL,
	//		 KARENA VIEW MEMILIKI SUB-PACKAGES. TERIMA KASIH
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		GuestDefault.display(s);
		
	}
	

}
