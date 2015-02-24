package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPartPopupController implements ActionListener{
	
	private InventoryModel model;
	private EditPartPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partId;
	private String partName;
	private String partNumber;
	private String vendor;
	private String unitQuantity;
	private String externalPartNumber;
	private int UIid;
	
	public EditPartPopupController(InventoryModel model, EditPartPopup editPartPopup) {
		this.model = model;
		this.itemP = editPartPopup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.dispose();
		} else if(command.equals(submitString)){
			partId = itemP.getPartId();
			partNumber = itemP.getPartNumber();
			partName = itemP.getPartName();
			vendor = itemP.getVendor();
			unitQuantity = itemP.getUnitQuantity();
			externalPartNumber = itemP.getExternalPartNumber();
			submit();					
		}
		
	}

	private void submit(){
		itemP.resetErrors();
		boolean error = false;
		Part part = new Part();
		
		/* set part id, can't be changed */
		if(!partId.isEmpty()){//this shouldn't be possible
			//first try to parse to int
			try{
				UIid = Integer.parseInt(partId);
			} catch(Exception NumberFormatException) {
				itemP.formatError(0);
				error = true;
			}
			part.setId(UIid);
		} else {
			itemP.formatError(0);
			error = true;
		}
		
		/* set part number */
		/* need to make sure no parts have the same part # */
		if( partNumber.length() < 20 && !partNumber.isEmpty() ){
			part.setPartNumber(partNumber);
		} else {
			itemP.formatError(1);
			error = true;
		}
		
		/* set part name */
		if( partName.length() < 255 && !partName.isEmpty() ){
			part.setPartName(partName);//already did the checks
		} else {
			itemP.formatError(2);
			error = true;
		}
		
		/* set vendor */
		if( vendor.length() < 255 || vendor.isEmpty() ){
			part.setVendor(vendor);
		} else { 
			itemP.formatError(3);
			error = true;
		}
		
		/* set unit quantity */
		if(unitQuantity.equals("Unknown")){
			itemP.formatError(4);
			error = true;
		} else{
			part.setUnitType(unitQuantity);
		}	
		
		/* set external part number */
		if( externalPartNumber.length() < 255 && !externalPartNumber.isEmpty() ){
			part.setExternalPartNumber(externalPartNumber);
		} else {
			itemP.formatError(5);
			error = true;
		}
		if(!error){
			/* to edit it we remove the old one and add a new item with the new info */
			model.removeElement( (itemP.getSelectedItem()).getPartNumber());//remove old item
			model.addElement(part);//add the new one
			itemP.closeWindow();
		}		
		return;
	}

}
