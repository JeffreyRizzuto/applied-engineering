package inventory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryView extends JFrame {

	private static final long serialVersionUID = 293596607766330824L;

	private InventoryModel model;
	private JPanel invPanel;
	private JScrollPane scrollPane;
	private JMenu invMenu;
	private JMenu modeMenu;
	private JMenu viewMenu;
	private JList partList;
	private JList itemList;
	private JPanel buttonPane;
	private int view = 0;//0 = part, 1 = item
	private ModeSwitcher mode;//0 = inv, 1 = prod
	
	private static final String editString = "edit";
	private static final String addString = "add";
    private static final String removeString = "remove";
	
	public InventoryView(InventoryModel model, ModeSwitcher mode) {
		super("Inventory");
		this.model = model;
		this.mode = mode;
		
		//set some JFrame settings
		setTitle("Parts");
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/* make componenets we need */
		
		//inventory scroll pane
		invPanel = new JPanel();
		invPanel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane(partList = model.getPartsList());
		invPanel.add(scrollPane);
		
		//button pane
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		// create the menu
		JMenuBar MenuBar = new JMenuBar();
		setJMenuBar(MenuBar);
		
		//first menu------------------------------
		invMenu = new JMenu("Menu");
		invMenu.setMnemonic('M');
		MenuBar.add(invMenu);
		
		//menu items
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		invMenu.add(exitItem);
		
		//second menu------------------------------
		modeMenu = new JMenu("Mode");
		modeMenu.setMnemonic('P');
		MenuBar.add(modeMenu);
		
		//menu items				
		JMenuItem inventoryMode = new JMenuItem("Inventory View");
		inventoryMode.setMnemonic('I');
		modeMenu.add(inventoryMode);
		
		JMenuItem productMode = new JMenuItem("Product View");
		productMode.setMnemonic('p');
		modeMenu.add(productMode);
		
		//third menu-------------------------------
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic('P');
		MenuBar.add(viewMenu);
		
		//menu items				
		JMenuItem parts = new JMenuItem("parts");
		parts.setMnemonic('p');
		viewMenu.add(parts);
		
		JMenuItem items = new JMenuItem("items");
		items.setMnemonic('i');
		viewMenu.add(items);
		
		
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
	
	public void registerListeners(InventoryMenuController Mcontrol, InventoryController Icontrol) { 
		Component[] components = invMenu.getMenuComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(Mcontrol);
			}
		}
		
		for (Component component : modeMenu.getMenuComponents()) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(Mcontrol);
			}
		}
		
		for (Component component : viewMenu.getMenuComponents()) {
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
		
		//add mouse listener for the list
		//scrollPane.addMouseListener(Icontrol);
		
		
	}

	
	public int getView() {
		return view;
	}
	
	public JList getCurrentPartList() {
		return partList;
	}
	
	public JList getCurrentItemList() {
		return itemList;
	}
	
	public void update() {
		if(view == 0) {
			scrollPane.setViewportView(partList = model.getPartsList());
		} else {
			scrollPane.setViewportView(itemList = model.getItemsList());
		}
	}
	
	public void changeView(int setting) {
		if(setting == 0) {
			this.view = setting;
			this.setTitle("Parts");
			scrollPane.setViewportView(partList = model.getPartsList());
		} else {
			this.view = setting;
			this.setTitle("Items");
			scrollPane.setViewportView(itemList = model.getItemsList());
		}		
	}
	
	public void changeMode() {
		mode.switchToProd();
	}
}
