package Inventory;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Inventory_scrollPane extends JPanel{

	private static final long serialVersionUID = 6246152661885910554L;
	
	private Inventory_model model;
	private Inventory_view view;
	private JScrollPane ScrollPane;

	public Inventory_scrollPane(Inventory_model model, Inventory_view view) {
		this.model = model;
		this.view = view;
		
		/* set the layout of the panel to BorderLayout */
		setLayout(new BorderLayout());
		
		/* add that list from model to a scroll pane in the main window */
		ScrollPane = new JScrollPane(model.getItemList());
		add(ScrollPane);
		
		this.setFocusable(true);
	}
}
