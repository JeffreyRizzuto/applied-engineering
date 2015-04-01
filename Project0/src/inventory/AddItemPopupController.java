/* MODIFY THIS */

package inventory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AddItemPopupController implements ActionListener {

	private InventoryModel model;
	private AddItemPopup itemP;

	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";

	private String part;
	private String quantity;
	private int UIquantity;
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

		if (model.getPartByNumber(part) != null) {// see if input is a part
			// we are good, continue
		} else if (model.getProductByNumber(part) != null) {// see if input
																// is a product
			System.out.println("WAS A PRODUCT");
			Product p = model.getProductByNumber(part);
			ArrayList<Part> parts = model.getProductParts(p.getNumber());
			Iterator it = parts.iterator();
			HashMap<String, Integer> partQuantitys = new HashMap();
			while (it.hasNext()) {// go over each part
				Part currentPart = (Part) it.next();
				String partNumber = currentPart.getPartNumber();
				int count = partQuantitys.containsKey(partNumber) ? partQuantitys
						.get(partNumber) : 0;
				partQuantitys.put(partNumber, count + 1);
			}
			for (String partNumber : partQuantitys.keySet()) {
				if (!model.checkPartInStock(model.getPartByNumber(partNumber),
						unitLocation, partQuantitys.get(partNumber)
								* UIquantity)) {
					error = true;
					break;
				}
			}

			if (!error) {
				for (String partNumber : partQuantitys.keySet()) {
					model.removePartFromStock(
							model.getPartByNumber(partNumber), unitLocation,
							partQuantitys.get(partNumber) * UIquantity);
				}
			}
			// model.checkPartInStock(it.next(), unitLocation, UIquantity);
		} else {
			System.out.println("was neither");
			itemP.formatError(2);
			error = true;
		}

		if (!error) {
			item.setPart(part);
			item.setUnitLocation(unitLocation);
			item.setQuantity(UIquantity);

			model.addItem(item);// add the new one
			itemP.closeWindow();
		}
	}
}
