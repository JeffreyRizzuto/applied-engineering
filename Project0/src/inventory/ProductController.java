package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ProductController implements ActionListener, MouseListener {

	private ProductModel model;
	private ProductView view;
	private JList productList;
	private Product selecetedProduct;
	private Session session;

	private static final String addString = "add";
	private static final String removeString = "remove";
	private static final String editString = "edit";
	private static final String partString = "parts";

	public ProductController(ProductModel pModel, ProductView pView) {
		this.model = pModel;
		this.view = pView;
		session = model.getLoggedInUser();
		this.productList = model.getProductList();

	}

	public void actionPerformed(ActionEvent event) {

		String command = event.getActionCommand();

		int selectedIndex = productList.getSelectedIndex();

		Product selectedProduct = null;
		if (!productList.isSelectionEmpty()) {
			selectedProduct = model.getProductAt(selectedIndex);
		}

		// edit--------------------------------------------
		if (command.equals(editString)) {
			System.out.println("edit pressed");
			if (!session.canAddProductTemplates) {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
				return;
			}
			// if nothing it selected we can't remove anything
			if (productList.isSelectionEmpty()) {
				return;
			}

			new EditProductPopup(model, model.getProductAt(selectedIndex));//selectedProduct isn't working????

			// add--------------------------------------------
		} else if (command.equals(addString)) {
			if (!session.canAddProductTemplates) {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
				return;
			}
			AddProductPopup add = new AddProductPopup(model);
			// remove--------------------------------------------
		} else if (command.equals(removeString)) {
			if (!session.canDeleteProductTemplates) {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
				return;
			}
			// if nothing it selected we can't remove anything
			if (productList.isSelectionEmpty()) {
				return;
			}
			DeletePopup delete = new DeletePopup(productList.getSelectedValue());
			if (delete.response()) {// yes
				model.removeProduct(selectedProduct);
			} else {// no
				return;
			}
			// parts--------------------------------------------
		} else if (command.equals(partString)) {
			if (!session.canAddProductTemplates) {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
				return;
			}
			if (productList.isSelectionEmpty()) {
				return;
			}
			ProductPartsPopup parts = new ProductPartsPopup(model,
					model.getAssociatedParts(selectedProduct.getId()), model.getProductAt(selectedIndex));
			parts.registerListeners(new ProductPartPopupController(parts,model));
		}
		 productList = model.getProductList();
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
