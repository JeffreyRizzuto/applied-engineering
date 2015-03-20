package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class InventoryMenuController extends KeyAdapter implements ActionListener  {
	private InventoryModel model;
	private InventoryView view;
	
	public InventoryMenuController(InventoryModel model, InventoryView view) {
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
			return;//do nothing, we are in this mode
		}
		if (command.equals("Product View")) {
			System.out.println("Changing modes");
			view.changeMode();
		}
		if (command.equals("parts")) {
			view.changeView(0);
		}
		if (command.equals("items")) {
			view.changeView(1);
		}
	}
}
