package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

public class Item_controller implements ActionListener{
	
	private Inventory_model model;
	private Item_popup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String partName;
	private String partNumber;
	private String vendor;
	private int quantity;
	
	public Item_controller(Inventory_model model, Item_popup item_popup) {
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
		//kind of want to fix this cause it looks weird
		if( partName.length() < 20 && !partName.isEmpty() ){
			if(model.checkElement(partName)){//if it already exists
				return;//really need to re ask for itemP here
			}
		}
		
		Item item = new Item();
		/* set part name */
		item.setPartName(partName);//already did the checks
		
		/* set part number */
		if( partNumber.length() < 255 && !partNumber.isEmpty() ){
			item.setPartNumber(partNumber);
		} else {
			return;//needs to be throw
		}
		
		/* set vendor */
		if( vendor.length() < 255 || vendor.isEmpty() ){
			item.setVendor(vendor);
		} else { 
			return;//needs to be throw
		}
		
		/* set quantity */
		if( quantity > 0){
			item.setQuantity(quantity);
		} else {
			return;//needs to be throw
		}
		
		model.addElement(item);
		itemP.closeWindow();
	}

}
