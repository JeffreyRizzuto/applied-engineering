/* MODIFY THIS */

package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemPopupController implements ActionListener{
	
	private InventoryModel model;
	private EditItemPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partName;
	private String partNumber;
	private String externalPartNumber;
	private String partSomething;
	private int itemId;
	private String vendor;
	private String quantity;
	private int UIquantity;
	private String unitType;
	private String unitLocation;
	
	public EditItemPopupController(InventoryModel model, EditItemPopup edit_popup) {
		this.model = model;
		this.itemP = edit_popup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.dispose();
		} else if(command.equals(submitString)){
			//partNumber = itemP.getPartNumber();
			//partName = itemP.getPartName();
			//vendor = itemP.getVendor();
			quantity = itemP.getQuantity();
			//unitType = itemP.getUnitType();
			//externalPartNumber = itemP.getExternalPartNumber();
			unitLocation = itemP.getUnitLocation();
			partSomething = "dunno on this";
			itemId = 1000;
			submit();					
		}
		
	}

	private void submit(){
		
		itemP.resetErrors();//reset the background color of textboxes for prior errors
		boolean error = false;
		
		//kind of want to fix this cause it looks weird
		/* set part name */
		Item item = new Item();
		
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
		/* if no error occurred, lets add it to the map */
		if(!error){
			/* to edit it we remove the old one and add a new item with the new info */
			model.removeElement( (itemP.getSelectedItem()).getPartName() );//remove old item
			model.addElement(item);//add the new one
			itemP.closeWindow();
		}
		
		//Set Unit location
		if(unitLocation.equals("Unknown")){
			itemP.formatError(6);
			error = true;
		} else{
			item.setUnitLocation(unitLocation);
		}
		
		//Set ID
		if(itemId<0){
			itemP.formatError(7);
			error = true;
		} else{
			item.setId(itemId);
		}
		
		//Set Part?
		if(partSomething.equals("")){
			itemP.formatError(8);
			error = true;
		}
		
		return;
		

	}

}
