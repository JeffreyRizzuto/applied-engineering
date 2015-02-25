package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.text.View;

public class InventoryController implements ActionListener, MouseListener {
	
	private InventoryModel model;
	private InventoryView view;
	
	 private ArrayList<Observer> observers = new ArrayList<Observer>();  
	
	private boolean alreadyEnabled = false;
	private JButton button;
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
    private JList partList;
    private JList itemList;
    private int clicksCount;
	
	public InventoryController(InventoryModel model, InventoryView view){
		this.model = model;
		this.view = view;
		this.partList = model.getPartsList();
		this.itemList = model.getItemsList();
		clicksCount=0;
	}

	//based on the command, will either open a add item, or remove item
	public void actionPerformed(ActionEvent event) {	
		
		String command = event.getActionCommand();
		//part mode
		if(view.getMode() == 0) {
			if(command.equals(editString)){
				//if nothing it selected we can't remove anything
				if(partList.isSelectionEmpty()){
					return;
				}
				EditPartPopup edit = new EditPartPopup(model, model.getPart(model.partToId(partList.getSelectedIndex())));//i'm sorry
				
			} else if(command.equals(addString)){
				AddPartPopup add = new AddPartPopup(model);
				
			} else if(command.equals(removeString)){
				//if nothing it selected we can't remove anything
				if(partList.isSelectionEmpty()){
					return;
				}
				//create delete prompt
				int index = partList.getSelectedIndex();
				DeletePartPopup delete = new DeletePartPopup(partList.getSelectedValue());
				if(delete.response()){//yes
					model.removePart(model.partToId(partList.getSelectedIndex()));
				} else {//no
					return;
				}
			}
		//item mode
		} else if (view.getMode() == 1){//sanity check
			if(command.equals(editString)){
				//if nothing it selected we can't remove anything
				if(itemList.isSelectionEmpty()){
					return;
				}
				EditItemPopup edit = new EditItemPopup(model,model.getItem((model.partToId(itemList.getSelectedIndex()))));//i'm sorry
				
			} else if(command.equals(addString)){
				AddItemPopup add = new AddItemPopup(model);
				
			} else if(command.equals(removeString)){
				//if nothing it selected we can't remove anything
				if(itemList.isSelectionEmpty()){
					return;
				}
				//create delete prompt
				int index = itemList.getSelectedIndex();
				DeletePartPopup delete = new DeletePartPopup(itemList.getSelectedValue());//part popup will work for item
				if(delete.response()){//yes
					model.removeitem((model.itemToId(itemList.getSelectedIndex())));
				} else {//no
					return;
				}
			}
		}
    }
	
	@Override
	public void mouseClicked(MouseEvent click) {
		JList invList = (JList) click.getSource();
		if (click.getClickCount() == 2) {
	          int index = invList.locationToIndex(click.getPoint());
	          if (index >= 0) {
	            Object o = invList.getModel().getElementAt(index);
	            //EditPartPopup edit = new EditPartPopup(model,model.getPart((o.toString())));
	          }
		}
	}
	
	    
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
