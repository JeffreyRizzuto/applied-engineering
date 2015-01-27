package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

public class InventoryController implements ActionListener {
	
	private InventoryModel model;
	private InventoryView view;
	
	private boolean alreadyEnabled = false;
	private JButton button;
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
    private JList list;
	
	public InventoryController(InventoryModel model, InventoryView view){
		this.model = model;
		this.view = view;
		this.list = model.getItemList();
	}

	//based on the command, will either open a add item, or remove item
	public void actionPerformed(ActionEvent event) {	
		
		String command = event.getActionCommand();
		
		if(command.equals(editString)){
			//if nothing it selected we can't remove anything
			if(list.isSelectionEmpty()){
				return;
			}
			EditPopup edit = new EditPopup(model,model.getElement((String) list.getSelectedValue()));
			
		} else if(command.equals(addString)){
			AddPopup add = new AddPopup(model);
			
		} else if(command.equals(removeString)){
			//if nothing it selected we can't remove anything
			if(list.isSelectionEmpty()){
				return;
			}
			//create delete prompt
			int index = list.getSelectedIndex();
			DeletePopup delete = new DeletePopup(list.getSelectedValue());
			if(delete.response()){//yes
				model.removeElement((String)list.getSelectedValue());
			} else {//no
				return;
			}
		}
		
    }
}
