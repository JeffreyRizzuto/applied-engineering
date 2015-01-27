package Inventory;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryView extends JFrame{

	private static final long serialVersionUID = 293596607766330824L;

	private InventoryModel model;
	private InventoryScrollPane invPanel;
	private JLabel statusBar;
	private JMenu invMenu;
	private JPopupMenu popupMenu;
	private JPanel buttonPane;
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
	
	// properties of the panel
	private int width, height;
	
	public InventoryView(InventoryModel model) {
		super("Inventory");
		this.model = model;
		
		//set some JFrame settings
		setTitle("Inventory");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/* make componenets we need */
		//inventory scroll pane
		invPanel = new InventoryScrollPane(model,this);
		//button pane
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
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
		add(invPanel, BorderLayout.CENTER);
		
		/*
		 *  add/delete buttons
		 */
		
		//make buttons
		JButton addButton = new JButton(addString);
		JButton removeButton = new JButton(removeString);
		JButton editButton = new JButton(editString);
		
		//add commands to buttons
		editButton.setActionCommand(editString);
		addButton.setActionCommand(addString);
		removeButton.setActionCommand(removeString);
		
		//add buttons to pane
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(editButton);
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		
		//add buttonPane to the JFrame
		add(buttonPane, BorderLayout.PAGE_END);
		
	}
	
	public void registerListeners(MenuController Mcontrol, InventoryController Icontrol) { 
		Component[] components = invMenu.getMenuComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(Mcontrol);
			}
		}
		
		components = buttonPane.getComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(Icontrol);
			}
		}
	}
}
