package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPopupController implements ActionListener{
	
	private InventoryModel model;
	private AddPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partId;
	private String partName;
	private String partNumber;
	private String vendor;
	private String quantity;
	private int UIid;
	private int UIquantity;
	private String unitQuantity;
	private String externalPartNumber;
	
	public AddPopupController(InventoryModel model, AddPopup item_popup) {
		this.model = model;
		this.itemP = item_popup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.dispose();
			return;
		} else if(command.equals(submitString)){
			partId = itemP.getPartId();
			partNumber = itemP.getPartNumber();
			partName = itemP.getPartName();
			vendor = itemP.getVendor();
			quantity = itemP.getQuantity();
			unitQuantity = itemP.getUnitQuantity();
			externalPartNumber = itemP.getExternalPartNumber();
			submit();					
		}
		
	}

	private void submit(){
		itemP.resetErrors();
		boolean error = false;
		Item item = new Item();
		//kind of want to fix this cause it looks weird
		if( partName.length() < 20 && !partName.isEmpty() ){
			if(model.checkElement(partName)){//if it already exists
				itemP.formatError(1);
				error = true;
			}
		}
		
		/* set part id */
		if(!partId.isEmpty() ){
			//first try to parse to int
			try{
				UIid = Integer.parseInt(partId);
			} catch(Exception NumberFormatException) {
				itemP.formatError(0);
				error = true;
			}
			if(model.checkIdExists(UIid)){
				itemP.formatError(0);
				error = true;
			} else {
				item.setId(UIid);
			}
		} else {
			itemP.formatError(0);
		}
		
		/* set part name */
		if( partName.length() < 20 && !partName.isEmpty() ){
			item.setPartName(partName);//already did the checks
		} else {
			itemP.formatError(1);
		}
		
		/* set part number */
		if( partNumber.length() < 255 && !partNumber.isEmpty() ){
			item.setPartNumber(partNumber);
		} else {
			itemP.formatError(2);
			error = true;
		}
		
		/* set external part number */
		if( externalPartNumber.length() < 255 && !externalPartNumber.isEmpty() ){
			item.setExternalPartNumber(externalPartNumber);
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
		/* set unit quantity */
		if(unitQuantity.equals("Unknown")){
			//throw error
		} else{
			item.setUnitType(unitQuantity);
		}
		//try to parse the field to an int, if error, throw a format error
		try{
			UIquantity = Integer.parseInt(quantity);
		} catch(Exception NumberFormatException) {
			itemP.formatError(4);
			error = true;
		}
		if( UIquantity > 0){
			item.setQuantity(UIquantity);
		} else {
			itemP.formatError(4);
			error = true;
		}
		
		if(!error){
			model.addElement(item);
			itemP.closeWindow();
		}
		
		return;
	}

}
