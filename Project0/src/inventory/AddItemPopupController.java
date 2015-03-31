/* MODIFY THIS */

package inventory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemPopupController implements ActionListener {

	private InventoryModel model;
	private AddItemPopup itemP;

	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";

	private String part;
	private String quantity;
	private int UIquantity;
	private int partId;
	private String unitLocation;

	public AddItemPopupController(InventoryModel model,
			AddItemPopup addItemPopup) {
		this.model = model;
		this.itemP = addItemPopup;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(cancelString)) {
			itemP.dispose();
			return;
		} else if (command.equals(submitString)) {
			quantity = itemP.getQuantity();
			unitLocation = itemP.getUnitLocation();
			part = itemP.getPartID();
			submit();
		}

	}

	// will redo this function
	private void submit() {
		itemP.resetErrors();

		itemP.resetErrors();
		boolean error = false;

		Item item = new Item();

		try {
			partId = Integer.parseInt(part);
			if (!model.checkPartIdExists(partId)) {
				itemP.formatError(2);
				error = true;
			}
		} catch (Exception NumberFormatException) {
			itemP.formatError(2);
			error = true;
		}

		if (unitLocation.equals("Unknown"))
			error = true;

		if (quantity.isEmpty()) {
			itemP.formatError(1);
			error = true;
		}

		try {
			UIquantity = Integer.parseInt(quantity);
			if (UIquantity <= 0) {
				itemP.formatError(1);
				error = true;
			}
		} catch (Exception NumberFormatException) {
			itemP.formatError(1);
			error = true;
		}

		if (!error) {
			item.setPart(partId);
			item.setUnitLocation(unitLocation);
			item.setQuantity(UIquantity);

			model.addItem(item);// add the new one
			itemP.closeWindow();
		}
	}
}
