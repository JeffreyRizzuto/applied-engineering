package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Popup;

public class EditProductPopupController implements ActionListener{
	
	private static final String submitString = "Submit";
	private static final String cancelString = "Cancel";

	private EditProductPopup popup;
	private ProductModel model;
	
	private String number;
	private String description;
	

	public EditProductPopupController(ProductModel model,
			EditProductPopup editProductPopup) {
		this.popup = editProductPopup;
		this.model = model;
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if(command.equals(cancelString)){
			popup.dispose();
			return;
		} else if(command.equals(submitString)){
			//partId = itemP.getPartId();
			number = popup.getNumber();
			description = popup.getDescription();
			submit();					
		}
		
	}
	
	private void submit(){
		
		boolean error = false;
		
		Product newProduct = new Product();
		
		if(number.startsWith("A") && !number.isEmpty()) {
			newProduct.setNumber(number);
		} else {
			popup.formatError(1);
			error = true;
		}
		if(!description.isEmpty()) {
			newProduct.setDescription(description);
		} else {
			popup.formatError(2);
			error = true;
		}
		
		if(!error) {
			model.removeProduct(popup.getProduct());
			model.addProduct(newProduct);
			popup.closeWindow();
		}
	}

}
