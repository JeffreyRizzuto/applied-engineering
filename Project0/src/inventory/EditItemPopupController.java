/* MODIFY THIS */

package inventory;

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
	private String part;
	private String itemId;
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
			itemId = itemP.getId();
			part = itemP.getPartID();
			quantity = itemP.getQuantity();
			unitLocation = itemP.getUnitLocation();
			
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
			itemP.formatError(1);
			error = true;
		}		
		//try to parse the field to an int, if error, throw a format error
		try{
			UIquantity = Integer.parseInt(quantity);
			item.setQuantity(UIquantity);
		} catch(Exception NumberFormatException) {
			itemP.formatError(1);
			error = true;
		}
		
		//Set Unit location
		if(unitLocation.equals("Unknown")){
			//itemP.formatError(6);
			error = true;
		} else{
			item.setUnitLocation(unitLocation);
		}
		
		//Set ID
		item.setId(Integer.parseInt(itemId));
		
		if(part.equals("")){
			itemP.formatError(3);
		} else {
			try {
				if(model.getPart(Integer.parseInt(part)) != null){
					item.setPart(Integer.parseInt(part));
				}
			} catch(Exception NumberFormatException) {
				itemP.formatError(3);
				error =equals(true);
			}
		}
		
		
		/* if no error occurred, lets add it to the map */
		if(!error){
			/* to edit it we remove the old one and add a new item with the new info */
			model.removeitem(item.getId());
			model.addItem(item);//add the new one
			itemP.closeWindow();
		}
		return;

	}

}
