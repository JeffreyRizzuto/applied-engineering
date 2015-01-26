package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Inventory_controller implements ActionListener {
	
	private Inventory_model model;
	private Inventory_view view;
	
	private boolean alreadyEnabled = false;
	private JButton button;
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
    
	
	public Inventory_controller(Inventory_model model, Inventory_view view){
		this.model = model;
		this.view = view;
	}

	//based on the command, will either open a add item, or remove item
	public void actionPerformed(ActionEvent event) {	
		
		String command = event.getActionCommand();
		
		if(command.equals(editString)){
			//if nothing it selected we can't remove anything
			if(model.isSelectionEmpty()){
				return;
			}
			
			Item_popup edit = new Item_popup(model);
			
		} else if(command.equals(addString)){
			Item_popup add = new Item_popup(model);
			
		} else if(command.equals(removeString)){
			//if nothing it selected we can't remove anything
			if(model.isSelectionEmpty()){
				return;
			}
			//create delete prompt
			int index = model.getSelectedIndex();
			Delete_popup delete = new Delete_popup(model.getSelectedValue());
			if(delete.response()){//yes
				model.removeElement((Item) model.getSelectedValue());
			} else {//no
				return;
			}
		}
		
    }
}
