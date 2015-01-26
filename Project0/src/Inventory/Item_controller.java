package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class Item_controller implements ActionListener{
	
	private Inventory_model model;
	private Item_popup itemP;
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";
	private String[] Input;

	public Item_controller(Inventory_model model, Item_popup item_popup) {
		this.model = model;
		this.itemP = item_popup;
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals(cancelString)){
			return;
		} else if(command.equals(submitString)){
			JTextField[] textFields = itemP.getTextField();
			for(int i=0;i<textFields.length;i++){
				Input[i] = textFields[i].getText();
			}
			//this item already exists
			if(model.elementExists(Input[1])){
				return;
			}
			
			Item item = new Item();
			if( Input[0].length() < 20 || !Input[0].isEmpty() ){
				item.setPartNumber(Input[0]);
			}
			if( Input[1].length() < 255 || !Input[1].isEmpty() ){
				item.setPartName(Input[1]);
			}
			if( Input[2].length() < 255 || Input[1].isEmpty() ){
				item.setVendor(Input[2]);
			}
			if( Integer.parseInt(Input[3]) > 0){
				item.setQuantity(Integer.parseInt(Input[3]));
			}
			model.addElement(item);
			
						
		}
		
	}

}
