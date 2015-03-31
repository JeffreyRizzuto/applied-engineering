package inventory;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class ProductPartsPopup extends JFrame{
	
	private HashMap<Integer,Part> parts;
	
	private ArrayList<String> partList;

	public ProductPartsPopup(ProductModel model, HashMap<Integer,Part> parts) {
		super("parts");
		this.parts = parts;
		setTitle("parts");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JList<String> partNames = new JList<String>();
		
		for(Part part : parts.values()) {
			partList.add(part.getPartName());
		}
		
		JPanel partPane = new JPanel();
		partPane.setLayout(new BorderLayout());
		JScrollPane partScroller = new JScrollPane(new JList(partList.toArray()));
		partPane.add(partScroller);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		JButton addButton = new JButton("add");
		JButton removeButton = new JButton("remove");
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		
		partPane.add(buttonPane);
		
	}
}
