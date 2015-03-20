package inventory;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ProductPartsPopup extends JFrame{
	
	private HashMap<Integer,Part> parts;

	public ProductPartsPopup(HashMap<Integer,Part> parts) {
		super("parts");
		this.parts = parts;
		setTitle("parts");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JList<String> partNames = new JList<String>();
		for(Part part : parts.values()) {
			
		}
		
		JPanel partPane = new JPanel();
		partPane.setLayout(new BorderLayout());
		JScrollPane partScroller = new JScrollPane();
		
	}

}
