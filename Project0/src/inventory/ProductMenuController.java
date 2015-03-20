package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class ProductMenuController extends KeyAdapter implements ActionListener  {
	private ProductModel model;
	private ProductView view;
	
	public ProductMenuController(ProductModel model, ProductView view) {
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		}
		if (command.equals("Inventory View")) {
			view.changeMode();
		}
		if (command.equals("Product View")) {
			return;//do nothin, we are in this mode
		}
	}
}
