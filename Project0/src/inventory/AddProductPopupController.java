package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Popup;

public class AddProductPopupController implements ActionListener{{
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";

	private AddProductPopup popup;
	private InventoryModel model;

	public AddProductPopupController(InventoryModel model,
			AddProductPopup addProductPopup) {
		this.popup = addProductPopup;
		this.model = model;
	}

	public void actionPerformed(ActionEvent arg0) {
		String command = event.getActionCommand();
		
		String number =
		if(command.equals(cancelString)){
			itemP.dispose();
			return;
		} else if(command.equals(submitString)){
			//partId = itemP.getPartId();
			String 
			submit();					
		}
		
	}

}
