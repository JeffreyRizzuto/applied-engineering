package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProductMenuController extends KeyAdapter implements ActionListener  {
	private ProductModel model;
	private ProductView view;
	private Session session;
	
	public ProductMenuController(ProductModel model, ProductView view) {
		this.model = model;
		this.view = view;
		session = model.getLoggedInUser();
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		}
		if (command.equals("Inventory View")) {
			if(session.canViewInventory) {
				view.changeMode();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
			}
				
		}
		if (command.equals("Product View")) {
			return;//do nothin, we are in this mode
		}
	}
}
