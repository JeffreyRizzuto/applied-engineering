package Inventory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Inventory_view extends JFrame {

	private static final long serialVersionUID = 293596607766330824L;

	private Inventory_model model;
	private Inventory_scrollPane invPanel;
	private JLabel statusBar;
	private JMenu invMenu;
	private JPopupMenu popupMenu;
	
	private static final String addString = "add";
    private static final String removeString = "remove";
	
	// properties of the panel
	private int width, height;
	
	public Inventory_view(Inventory_model model) {
		super("Inventory");
		this.model = model;
		
		//set some JFrame settings
		setTitle("Inventory");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// create the menu
		JMenuBar MenuBar = new JMenuBar();
		setJMenuBar(MenuBar);
		
		invMenu = new JMenu("Menu");
		invMenu.setMnemonic('M');
		MenuBar.add(invMenu);
		
		//menu items
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		invMenu.add(exitItem);
		
		/*
		 * Panel that the inventory is in
		 */
		invPanel = new Inventory_scrollPane(model,this);
		add(invPanel, BorderLayout.CENTER);
		
		/*
		 * Make Panel than the add/delete buttons
		 */
		//make pane
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
		//make buttons
		JButton addButton = new JButton(addString);
		JButton removeButton = new JButton(removeString);
		//add listeners to buttons
		
		//add buttons to pane
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		//add buttonPane to the JFrame
		add(buttonPane, BorderLayout.PAGE_END);
		
	}
	
	public void display_items() {
		
		
	}
}
