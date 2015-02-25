/* MODIFY THIS */

package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemPopupController implements ActionListener{
	
	private InventoryModel model;
	private AddItemPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partName;
	private String partNumber;
	private String externalPartNumber;
	private String part;
	private String itemId;
	private String vendor;
	private String quantity;
	private int UIquantity;
	private String unitType;
	private String unitLocation;
	
	public AddItemPopupController(InventoryModel model, AddItemPopup addItemPopup) {
		this.model = model;
		this.itemP = addItemPopup;		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			itemP.dispose();
			return;
		} else if(command.equals(submitString)){
			quantity = itemP.getQuantity();
			unitLocation = itemP.getUnitLocation();
			part = itemP.getPartID();
			itemId = itemP.getId();
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
			item.setQuantity(UIquantity);
		} catch(Exception NumberFormatException) {
			itemP.formatError(7);
			error = true;
		}
		
		//Set Unit location
		if(unitLocation.equals("Unknown")){
			itemP.formatError(6);
			error = true;
		} else{
			item.setUnitLocation(unitLocation);
		}
		
		//Set ID
		try {
			item.setId(Integer.parseInt(itemId));
			error = true;
		} catch(Exception NumberFormatException) {
			itemP.formatError(7);
			error = true;
		}
		
		//Set Part?
		if(part.equals("")){
			itemP.formatError(8);
			error = true;
		} else {
			item.setPart(Integer.parseInt(part));
		}
		
		
		/* if no error occurred, lets add it to the map */
		if(!error){
			/* to edit it we remove the old one and add a new item with the new info */
			model.addItem(item);//add the new one
			itemP.closeWindow();
		}
		return;

	}

}
