package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JList;

public class InventoryController implements ActionListener, MouseListener {
	
	private InventoryModel model;
	private InventoryView view;
	
	private boolean alreadyEnabled = false;
	private JButton button;
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
    private JList list;
    private int clicksCount;
	
	public InventoryController(InventoryModel model, InventoryView view){
		this.model = model;
		this.view = view;
		this.list = model.getItemList();
		clicksCount=0;
	}

	//based on the command, will either open a add item, or remove item
	public void actionPerformed(ActionEvent event) {	
		
		String command = event.getActionCommand();
		
		if(command.equals(editString)){
			//if nothing it selected we can't remove anything
			if(list.isSelectionEmpty()){
				return;
			}
			EditPartPopup edit = new EditPartPopup(model,model.getElement((String) list.getSelectedValue()));
			
		} else if(command.equals(addString)){
			AddPartPopup add = new AddPartPopup(model);
			
		} else if(command.equals(removeString)){
			//if nothing it selected we can't remove anything
			if(list.isSelectionEmpty()){
				return;
			}
			//create delete prompt
			int index = list.getSelectedIndex();
			DeletePartPopup delete = new DeletePartPopup(list.getSelectedValue());
			if(delete.response()){//yes
				model.removeElement((String)list.getSelectedValue());
			} else {//no
				return;
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
	            EditPartPopup edit = new EditPartPopup(model,model.getElement((o.toString())));
	          }
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
