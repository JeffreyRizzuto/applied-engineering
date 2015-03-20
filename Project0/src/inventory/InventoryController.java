/* this is old code from the start of the project, when I kinda of hacked it together
 * to make it work, eventually I want to update this code with the new format of the controller
 * like productController
 */


package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private Part selectedPart;

	public InventoryController(InventoryModel model, InventoryView view) {
		this.model = model;
		this.view = view;
		this.partList = model.getPartsList();
		this.itemList = model.getItemsList();
		clicksCount = 0;
	}

	// based on the command, will either open a add item, or remove item
	public void actionPerformed(ActionEvent event) {

		String command = event.getActionCommand();

		if (!partList.isSelectionEmpty()) {
			selectedPart = model.getPart(model.partToId(partList
					.getSelectedIndex()));// ew
		}
		// part mode
		if (view.getView() == 0) {
			if (command.equals(editString)) {
				// if nothing it selected we can't remove anything
				if (partList.isSelectionEmpty()) {
					return;
				}

				// first lets make sure the part isn't being edited elsewhere
				if (model.checkLock(selectedPart.getId())) {
					// will show a brief message, with an OK button. Ignore the
					// edit command for now
					JOptionPane.showMessageDialog(new JFrame(),
							"That part is in use elsewhere");
					return;
				}

				// lock the part first
				model.lockPart(selectedPart.getId());
				// show edit popup
				EditPartPopup edit = new EditPartPopup(model, selectedPart);
				/*
				 * I want to add a listener here for when the window closes to
				 * free the lock, but if the part is changed, the part we have
				 * here no longer exists, which is a problem, so unlocking will
				 * be done in editPaprtPopupController for now
				 */

				/*
				 * edit.addWindowListener(new WindowAdapter() { public void
				 * windowClosing(WindowEvent e) {
				 * model.unlockPart(selectedPart.getId()); } }
				 */

			} else if (command.equals(addString)) {
				AddPartPopup add = new AddPartPopup(model);

			} else if (command.equals(removeString)) {
				// if nothing it selected we can't remove anything
				if (partList.isSelectionEmpty()) {
					return;
				}

				// first lets make sure the part were deleting is opened
				// somewhere
				if (model.checkLock(selectedPart.getId())) {
					// will show a brief message, with an OK button. Ignore the
					// edit command for now
					JOptionPane.showMessageDialog(new JFrame(),
							"That part is in use elsewhere");
					return;
				}
				// Delete stuff
				//first make sure chosen part isn't part of a product
				if(model.checkPartAssociation(model.partToId(partList.getSelectedIndex()))) {
					//show
					JOptionPane.showMessageDialog(new JFrame(),
							"That part is part of a product template and can't be deleted");
				} else {
					int index = partList.getSelectedIndex();
					DeletePopup delete = new DeletePopup(
							partList.getSelectedValue());
					if (delete.response()) {// yes
						model.removePart(model.partToId(partList.getSelectedIndex()));
					} else {// no
						return;
					}
				}
			}
			// item mode
		} else if (view.getView() == 1) {// sanity check
			if (command.equals(editString)) {
				// if nothing it selected we can't remove anything
				if (itemList.isSelectionEmpty()) {
					return;
				}
				EditItemPopup edit = new EditItemPopup(model,
						model.getItem((model.partToId(itemList
								.getSelectedIndex()))));// i'm sorry

			} else if (command.equals(addString)) {
				AddItemPopup add = new AddItemPopup(model);

			} else if (command.equals(removeString)) {
				// if nothing it selected we can't remove anything
				if (itemList.isSelectionEmpty()) {
					return;
				}
				// create delete prompt
				int index = itemList.getSelectedIndex();
				DeletePopup delete = new DeletePopup(
						itemList.getSelectedValue());// part popup will work for
														// item
				if (delete.response()) {// yes
					model.removeitem((model.itemToId(itemList
							.getSelectedIndex())));
				} else {// no
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
				// EditPartPopup edit = new
				// EditPartPopup(model,model.getPart((o.toString())));
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
