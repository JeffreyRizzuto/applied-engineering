package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPartPopupController implements ActionListener{
	
	private InventoryModel model;
	private AddPartPopup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partId;
	private String partName;
	private String partNumber;
	private String vendor;
	private String unitQuantity;
	private String externalPartNumber;
	private int UIid;
	
	public AddPartPopupController(InventoryModel model, AddPartPopup item_popup) {
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
			unitQuantity = itemP.getUnitQuantity();
			externalPartNumber = itemP.getExternalPartNumber();
			submit();					
		}
		
	}

	private void submit(){
		itemP.resetErrors();
		boolean error = false;
		Part part = new Part();
		
		/* set part id */
		if(!partId.isEmpty() ){
			//first try to parse to int
			try{
				UIid = Integer.parseInt(partId);
			} catch(Exception NumberFormatException) {
				itemP.formatError(0);
				error = true;
			}
			if(model.checkPartIdExists(UIid)){
				itemP.formatError(0);
				error = true;
			} else {
				part.setId(UIid);
			}
		} else {
			itemP.formatError(0);
			error = true;
		}
		
		/* set part number */
		/* need to make sure no parts have the same part # */
		if( partNumber.length() < 20 && !partNumber.isEmpty() && !model.checkPartNumber(partNumber) && partNumber.startsWith("P")) {
			part.setPartNumber(partNumber);
		} else {
			itemP.formatError(1);
			error = true;
		}
		
		/* set part name */
		if( partName.length() < 255 && !partName.isEmpty()){
			if(model.checkPartName(partName)) {
				//must throw a quick message to the user saying part name exists (is allowed though)
			}
			part.setPartName(partName);
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
			model.addPart(part);
			itemP.closeWindow();
		}
		
		return;
	}

}
