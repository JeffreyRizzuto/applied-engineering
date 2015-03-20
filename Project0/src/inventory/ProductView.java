package inventory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProductView extends JFrame {

	private static final long serialVersionUID = 293596607766330824L;

	private ProductModel model;
	private JPanel invPanel;
	private JScrollPane scrollPane;
	private JLabel statusBar;
	private JMenu prodMenu;
	private JMenu modeMenu;
	private JPopupMenu popupMenu;
	private JPanel buttonPane;
	private int view = 0;// 0 = part, 1 = item
	private ModeSwitcher mode;
	
	private static final String addString = "add";
	private static final String removeString = "remove";
	private static final String editString = "edit";
	private static final String partString = "parts";
	
	
	public ProductView(ProductModel model, ModeSwitcher mode) {
		super("Products");
		this.model = model;
		this.mode = mode;

		// set some JFrame settings
		setVisible(false);// start hidden
		setTitle("Products");
		setSize(600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		/* make componenets we need */

		// inventory scroll pane
		invPanel = new JPanel();
		invPanel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane(model.getProductList());
		invPanel.add(scrollPane);

		// button pane
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

		// create the menu
		JMenuBar MenuBar = new JMenuBar();
		setJMenuBar(MenuBar);

		// first menu------------------------------
		prodMenu = new JMenu("Menu");
		prodMenu.setMnemonic('M');
		MenuBar.add(prodMenu);

		// menu items
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		prodMenu.add(exitItem);

		// second menu------------------------------
		modeMenu = new JMenu("Mode");
		modeMenu.setMnemonic('M');
		MenuBar.add(modeMenu);

		// menu items
		JMenuItem inventoryMode = new JMenuItem("Inventory View");
		inventoryMode.setMnemonic('I');
		modeMenu.add(inventoryMode);

		JMenuItem productMode = new JMenuItem("Product View");
		productMode.setMnemonic('p');
		modeMenu.add(productMode);

		/*
		 * Panel that the inventory is in
		 */
		add(invPanel, BorderLayout.CENTER);

		/*
		 * add/delete buttons
		 */

		// make buttons
		JButton addButton = new JButton(addString);
		JButton removeButton = new JButton(removeString);
		JButton editButton = new JButton(editString);
		JButton partsButton = new JButton(partString);

		// add commands to buttons
		editButton.setActionCommand(editString);
		addButton.setActionCommand(addString);
		removeButton.setActionCommand(removeString);
		partsButton.setActionCommand(partString);

		// add buttons to pane
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(editButton);
		buttonPane.add(addButton);
		buttonPane.add(removeButton);
		buttonPane.add(partsButton);

		// add buttonPane to the JFrame
		add(buttonPane, BorderLayout.PAGE_END);

	}

	public void registerListeners(ProductController prodControl,
			ProductMenuController prodMenuControl) {
		Component[] components = prodMenu.getMenuComponents();
		for (Component component : prodMenu.getMenuComponents()) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(prodControl);
			}
		}
		
		for (Component component : modeMenu.getMenuComponents()) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(prodControl);
			}
		}

		components = buttonPane.getComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(prodMenuControl);
			}
		}

		// add mouse listener for the list
		// scrollPane.addMouseListener(Icontrol);

	}

	public void update() {
		scrollPane.setViewportView(model.getProductList());
	}

	public void changeMode() {
		mode.switchToInv();
	}

}
