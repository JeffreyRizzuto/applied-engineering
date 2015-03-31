package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ProductController implements ActionListener, MouseListener {

	private ProductModel model;
	private ProductView view;
	private JList productList;
	private Product selecetedProduct;
	
	private static final String addString = "add";
	private static final String removeString = "remove";
	private static final String editString = "edit";
	private static final String partString = "parts";

	public ProductController(ProductModel pModel, ProductView pView) {
		this.model = pModel;
		this.view = pView;
		this.productList = model.getProductList();

	}

	public void actionPerformed(ActionEvent event) {

		String command = event.getActionCommand();
		
		int selectedIndex = productList.getSelectedIndex();
		
		Product selectedProduct = null;
		if (!productList.isSelectionEmpty()) {
			selectedProduct = model.getProductAt(selectedIndex);
		}

		//edit--------------------------------------------
		if (command.equals(editString)) {
			// if nothing it selected we can't remove anything
			if (productList.isSelectionEmpty()) {
				return;
			}

			EditProductPopup edit = new EditProductPopup(model, selecetedProduct);

		//add--------------------------------------------
		} else if (command.equals(addString)) {
			AddProductPopup add = new AddProductPopup(model);
		//remove--------------------------------------------
		} else if (command.equals(removeString)) {
			// if nothing it selected we can't remove anything
			if (productList.isSelectionEmpty()) {
				return;
			}
			DeletePopup delete = new DeletePopup(
					productList.getSelectedValue());
			if (delete.response()) {// yes
				model.removeProduct(selectedProduct);
			} else {// no
				return;
			}
		//parts--------------------------------------------
		} else if (command.equals(partString)) {
			if (productList.isSelectionEmpty()) {
				return;
			}
			ProductPartsPopup parts = new ProductPartsPopup(model, model.getAssociatedParts(selectedProduct.getId()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
