package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPopupController implements ActionListener{
	
	private InventoryModel model;
	private AddPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partName;
	private String partNumber;
	private String vendor;
	private int quantity;
	
	public AddPopupController(InventoryModel model, AddPopup item_popup) {
		this.model = model;
		this.itemP = item_popup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.closeWindow();//close the window
			return;
		} else if(command.equals(submitString)){
			partNumber = itemP.getPartNumber();
			partName = itemP.getPartName();
			vendor = itemP.getVendor();
			quantity = itemP.getQuantity();
			submit();					
		}
		
	}

	private void submit(){
		itemP.resetErrors();
		//kind of want to fix this cause it looks weird
		if( partName.length() < 20 && !partName.isEmpty() ){
			if(model.checkElement(partName)){//if it already exists
				itemP.formatError(1);
				return;
			}
		}
		
		Item item = new Item();
		/* set part name */
		item.setPartName(partName);//already did the checks
		
		/* set part number */
		if( partNumber.length() < 255 && !partNumber.isEmpty() ){
			item.setPartNumber(partNumber);
		} else {
			itemP.formatError(2);
			return;
		}
		
		/* set vendor */
		if( vendor.length() < 255 || vendor.isEmpty() ){
			item.setVendor(vendor);
		} else { 
			itemP.formatError(3);
			return;
		}
		
		/* set quantity */
		if( quantity > 0){
			item.setQuantity(quantity);
		} else {
			itemP.formatError(4);
			return;
		}
		
		model.addElement(item);
		itemP.closeWindow();
	}

}
