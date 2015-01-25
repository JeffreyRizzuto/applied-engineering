package Inventory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Inventory_view extends JFrame {

	private static final long serialVersionUID = 293596607766330824L;

	private Inventory_model model;
	private Inventory_panel invPanel;
	private JLabel statusBar;
	private JMenu invMenu;
	private JPopupMenu popupMenu;
	
	// properties of the panel
	private int width, height;
	
	public Inventory_view(Inventory_model model) {
		super("Inventory");
		this.model = model;
		
		// create the menu
		invMenu = new JMenu("Menu");
		invMenu.setMnemonic('M');
		//menu items
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		invMenu.add(exitItem);
		
		/*
		 * Panel that the inventory is in
		 */
		invPanel = new Inventory_panel(model,this);
		add(invPanel, BorderLayout.CENTER);
		invPanel.setBackground(Color.WHITE);
		Dimension size = invPanel.getSize();
		
		
		setTitle("Inventory");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void display_items() {
		
		
	}
}
