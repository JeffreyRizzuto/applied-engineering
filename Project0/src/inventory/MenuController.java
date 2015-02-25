package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MenuController extends KeyAdapter implements ActionListener  {
	private InventoryModel model;
	private InventoryView view;
	
	public MenuController(InventoryModel model, InventoryView view) {
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		}
		if (command.equals("Toggle")) {
			view.changeMode();
		}
	}
}
