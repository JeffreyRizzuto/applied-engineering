package Inventory;

import javax.swing.JFrame;

public class InventoryManager{

	public static void main(String args[]) {
		InventoryModel model = new InventoryModel();
		InventoryView view = new InventoryView(model);
		InventoryController Icontrol = new InventoryController(model, view);
		MenuController Mcontrol = new MenuController(model, view);
		view.registerListeners(Mcontrol, Icontrol);
		//add controllers
		
		
		/* start the app */
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(400, 300);
		view.setVisible(true);
		
	}

}
