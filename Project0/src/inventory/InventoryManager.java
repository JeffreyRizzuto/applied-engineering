package inventory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.Timer;
import javax.swing.JFrame;

import com.mysql.jdbc.Connection;


public class InventoryManager{
	public static final int INV_ID = 1;

	public static void main(String args[]) {
		
		//authenticate
		Session session = Authenticator.authenticate("tj@cab.net", "hunter1");
		
		//create the database stuff
		Gateway pdg = null;
		try {
			pdg = new GatewaySQL(INV_ID, Connection.TRANSACTION_REPEATABLE_READ);
		} catch(GatewayException e) {
			System.out.println("Error creating DB connection: " + e.getMessage());
			System.exit(0);
		}
		
		//models
		InventoryModel iModel = new InventoryModel(pdg,session);
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
		
		
		//observer
		ListObserver o1 = new ListObserver();
		iModel.registerObserver(o1);
		o1.setView(iView);
		o1.setView(pView);
		ListObserver o2 = new ListObserver();
		pModel.registerObserver(o2);
		o2.setView(iView);
		o2.setView(pView);
		
		//get screen dimensions so we can have app pop up in middle of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		/* start the app */
		iView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iView.setSize(400, 300);
		iView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
		pView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
		iView.setVisible(true);
		
	}

}
