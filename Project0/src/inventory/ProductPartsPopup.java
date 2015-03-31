package inventory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ProductPartsPopup extends JFrame{
	
	private ArrayList<Part> parts;
	DefaultListModel partNames;
	JPanel buttonPane;
	JTextField input;
	Product currentProduct;

	public ProductPartsPopup(ProductModel model, ArrayList<Part> parts, Product product) {
		super("parts");
		this.parts = parts;
		this.currentProduct = product;
		setTitle("parts");
		setSize(400,300);
		
		partNames = new DefaultListModel();
		
		Iterator it = parts.iterator();
		
		while(it.hasNext()) {
			partNames.addElement(((Part) it.next()).getPartName());
		}
		
		JPanel partPane = new JPanel();
		partPane.setLayout(new BorderLayout());
		JScrollPane partScroller = new JScrollPane(new JList(partNames));
		partPane.add(partScroller);
		
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		JButton addButton = new JButton("add");
		addButton.setActionCommand("add");
		input = new JTextField();
		buttonPane.add(input);
		JButton removeButton = new JButton("remove");
		removeButton.setActionCommand("remove");
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		
		partPane.add(buttonPane, BorderLayout.PAGE_END);
		
		add(partPane, BorderLayout.CENTER);
		
		this.setVisible(true);		
	}
	
	public void registerListeners(ProductPartPopupController ppController) { 
		Component[] components = buttonPane.getComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(ppController);
			}
		}	
	}
	
	public String getInput() {
		return input.getText();
	}
	
	public Product getProduct() {
		return currentProduct;
	}
	
	public void closeWindow() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
