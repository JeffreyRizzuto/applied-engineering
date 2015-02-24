package inventory;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JFrame;

import com.mysql.jdbc.Connection;


public class InventoryManager{
	public static final int INV_ID = 1;

	public static void main(String args[]) {
		//create the RDG for the inventory
		InventoryGateway pdg = null;
		try {
			pdg = new InventoryGatewaySQL(INV_ID, Connection.TRANSACTION_REPEATABLE_READ);
			
		} catch(GatewayException e) {
			System.out.println("Error creating DB connection: " + e.getMessage());
			System.exit(0);
		}
		
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
