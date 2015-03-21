package inventory;

import java.awt.event.*;

import javax.swing.Timer;
import javax.swing.JFrame;

import com.mysql.jdbc.Connection;


public class InventoryManager{
	public static final int INV_ID = 1;

	public static void main(String args[]) {
		//create the RDG for the inventory
		Gateway pdg = null;
		try {
			pdg = new GatewaySQL(INV_ID, Connection.TRANSACTION_REPEATABLE_READ);
		} catch(GatewayException e) {
			System.out.println("Error creating DB connection: " + e.getMessage());
			System.exit(0);
		}
		
		//models
		InventoryModel iModel = new InventoryModel(pdg);
		ProductModel pModel = new ProductModel(pdg);
		
		//switcher
		ModeSwitcher mode = new ModeSwitcher();
		
		//inventory
		InventoryView iView = new InventoryView(iModel, mode);
		InventoryController invControl = new InventoryController(iModel, iView);
		InventoryMenuController invMenuControl =  new InventoryMenuController(iModel, iView);
		iView.registerListeners(invMenuControl, invControl);
		//products
		ProductView pView = new ProductView(pModel, mode);
		ProductController prodControl = new ProductController(pModel, pView);
		ProductMenuController prodMenuControl = new ProductMenuController(pModel,pView);
		pView.registerListeners(prodControl, prodMenuControl);
		
		//register the views with the mode switcher
		mode.registerInv(iView);
		mode.registerProd(pView);
		
		
		mode.registerInv(iView);
		mode.registerProd(pView);
		
		
		
		//observer
		ListObserver o1 = new ListObserver();
		iModel.registerObserver(o1);
		o1.setView(iView);
		ListObserver o2 = new ListObserver();
		pModel.registerObserver(o2);
		o2.setView(pView);
		
		
		/* start the app */
		iView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iView.setSize(400, 300);
		iView.setVisible(true);
		
	}

}
