package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPartPopupController implements ActionListener {

	ProductPartsPopup popup;
	ProductModel model;

	public ProductPartPopupController(ProductPartsPopup popup,
			ProductModel model) {
		this.popup = popup;
		this.model = model;
	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals("add")) {
			if (model.checkPart(popup.getInput())) {
				model.addProductPart(popup.getProduct(),
						model.getPart(popup.getInput()));
			}
		}

		if (command.equals("remove")) {
			if (model.checkPart(popup.getInput())) {
				model.addProductPart(popup.getProduct(),
						model.getPart(popup.getInput()));
			}
		}
	}
}
