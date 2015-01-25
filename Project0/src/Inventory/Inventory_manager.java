package Inventory;

import javax.swing.JFrame;

public class Inventory_manager{

	public static void main(String args[]) {
		Inventory_model model = new Inventory_model();
		Inventory_view view = new Inventory_view(model);
		//add controllers
		
		
		/* start the app */
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(400, 300);
		view.setVisible(true);
		
	}

}
