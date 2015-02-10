package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPopupController implements ActionListener{
	
	private InventoryModel model;
	private EditPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partName;
	private String partNumber;
	private String vendor;
	private String quantity;
	private int UIquantity;
	
	public EditPopupController(InventoryModel model, EditPopup edit_popup) {
		this.model = model;
		this.itemP = edit_popup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.dispose();
		} else if(command.equals(submitString)){
			partNumber = itemP.getPartNumber();
			partName = itemP.getPartName();
			vendor = itemP.getVendor();
			quantity = itemP.getQuantity();
			submit();					
		}
		
	}

	private void submit(){
		
		itemP.resetErrors();//reset the background color of textboxes for prior errors
		boolean error = false;
		
		//kind of want to fix this cause it looks weird
		/* set part name */
		Item item = new Item();
		if( partName.length() < 255 && !partName.isEmpty() ){
			item.setPartName(partName);//already did the checks
		} else{
			itemP.formatError(1);
			error = true;
		}
		
		/* set part number */
		if( partNumber.length() < 20 && !partNumber.isEmpty() ){
			item.setPartNumber(partNumber);
		} else {
			itemP.formatError(2);
			error = true;
		}
		
		/* set vendor */
		if( vendor.length() < 255 || vendor.isEmpty() ){
			item.setVendor(vendor);
		} else { 
			itemP.formatError(3);
			error = true;
		}
		
		/* set quantity */
		if(quantity.isEmpty()){//first check if empty
			itemP.formatError(4);
			error = true;
		}
		//try to parse the field to an int, if error, throw a format error
		try{
			UIquantity = Integer.parseInt(quantity);
		} catch(Exception NumberFormatException) {
			itemP.formatError(4);
			error = true;
		}
		if( UIquantity >= 0){
			item.setQuantity(UIquantity);
		} else {//shouldn't happen since using unsigned
			itemP.formatError(4);//needs to be throw
			error = true;
		}
		/* if no error occurred, lets add it to the map */
		if(!error){
			/* to edit it we remove the old one and add a new item with the new info */
			model.removeElement( (itemP.getSelectedItem()).getPartName() );//remove old item
			model.addElement(item);//add the new one
			itemP.closeWindow();
		}
		
		return;
		

	}

}
