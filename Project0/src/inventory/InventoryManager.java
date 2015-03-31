package inventory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JFrame;

import com.mysql.jdbc.Connection;


public class InventoryManager{
	public static final int INV_ID = 1;

	public static void main(String args[]) {
		
		//authenticate
		Session session = Authenticator.authenticate("rn@cab.net", "hunter1");
		
		//create the database stuff
		Gateway pdg = null;
		try {
			pdg = new GatewaySQL(INV_ID, Connection.TRANSACTION_REPEATABLE_READ);
		} catch(GatewayException e) {
			System.out.println("Error creating DB connection: " + e.getMessage());
			System.exit(0);
		}
		
		//models
		InventoryModel iModel = null;
		ProductModel pModel = null;
		if(session.canViewInventory || session.canViewParts)
			iModel = new InventoryModel(pdg,session);
		if(session.canViewProductTemplates)
			pModel = new ProductModel(pdg, session);
		
		//switcher
		ModeSwitcher mode = new ModeSwitcher(session);
		
		//inventory
		InventoryView iView = null;
		ProductView pView = null;
		if(session.canViewInventory) {
			iView = new InventoryView(iModel, mode);
			InventoryController invControl = new InventoryController(iModel, iView);
			InventoryMenuController invMenuControl =  new InventoryMenuController(iModel, iView);
			iView.registerListeners(invMenuControl, invControl);
		}
		
		if(session.canViewProductTemplates) {
			//products
			pView = new ProductView(pModel, mode);
			ProductController prodControl = new ProductController(pModel, pView);
			ProductMenuController prodMenuControl = new ProductMenuController(pModel,pView);
			pView.registerListeners(prodControl, prodMenuControl);
		}
		
		
		
		//register the views with the mode switcher
		mode.registerInv(iView);
		mode.registerProd(pView);		
		
		
		//get screen dimensions so we can have app pop up in middle of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		/* start the app */
		if(session.canViewInventory) {
			iView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iView.setSize(400, 300);
			pView.setSize(400,300);
			iView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			pView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			iView.setVisible(true);
		} else if(session.canViewParts) {
			iView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iView.setSize(400, 300);
			pView.setSize(400,300);
			iView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			pView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			iView.changeMode();
			iView.setVisible(true);
		} else if(session.canViewProductTemplates) {
			pView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			iView.setSize(400, 300);
			pView.setSize(400,300);
			iView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			pView.setLocation(dim.width/2-400/2, dim.height/2-300/2);
			pView.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Access Denied: Invalid User");
			System.exit(0);
		}
		
		//observer
		ListObserver o1 = new ListObserver();
		iModel.registerObserver(o1);
		o1.setView(iView);
		o1.setView(pView);
		ListObserver o2 = new ListObserver();
		pModel.registerObserver(o2);
		o2.setView(iView);
		o2.setView(pView);
	}

}
