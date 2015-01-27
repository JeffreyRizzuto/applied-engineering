package Inventory;

import javax.swing.JFrame;

public class Inventory_manager{

	public static void main(String args[]) {
		InventoryModel model = new InventoryModel();
		InventoryView view = new InventoryView(model);
		//add controllers
		
		
		/* start the app */
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(400, 300);
		view.setVisible(true);
		
	}

}
