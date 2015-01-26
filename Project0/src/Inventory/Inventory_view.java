package Inventory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Inventory_view extends JFrame implements ListSelectionListener{

	private static final long serialVersionUID = 293596607766330824L;

	private Inventory_model model;
	private Inventory_scrollPane invPanel;
	private JLabel statusBar;
	private JMenu invMenu;
	private JPopupMenu popupMenu;
	private Inventory_controller controller;
	
	private static final String editString = "edit";
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
		
		//make pane (with a boxlayout)
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		//make buttons
		JButton addButton = new JButton(addString);
		JButton removeButton = new JButton(removeString);
		JButton editButton = new JButton(editString);
		
		//add listeners to buttons
		controller = new Inventory_controller(model, this);//listener for buttons
		editButton.setActionCommand(editString);
		editButton.addActionListener(controller);
		addButton.addActionListener(controller);
		addButton.setActionCommand(addString);
		removeButton.setActionCommand(removeString);
		removeButton.addActionListener(controller);
		
		//add buttons to pane
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(editButton);
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		
		//add buttonPane to the JFrame
		add(buttonPane, BorderLayout.PAGE_END);
		
	}
	
	public void display_items() {
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
