package Inventory;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class Inventory_model extends JList{

 
	private boolean item_open;
	private JList list;
	private DefaultListModel Inventory_model;
	
	public Inventory_model(){
		
		/* Create the list items will be shown in */
		//need this to be item objects in this JList?
		Inventory_model = new DefaultListModel();
		
		//hard code just strings for now with a DefaultListModel
		
		setModel(Inventory_model);
		addElement("Jane Doe");
		addElement("John Smith");
		addElement("Kathy Green");
		addElement("Jane Doe");
		addElement("John Smith");
		addElement("Kathy Green");
		addElement("Jane Doe");
		addElement("John Smith");
		addElement("Kathy Green");
		addElement("Jane Doe");
		addElement("John Smith");
		addElement("Kathy Green");
		addElement("Jane Doe");
		addElement("John Smith");
		addElement("Kathy Green");
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		
		/*Create the list */
		//list = new JList(Inventory_model);
		//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setLayoutOrientation(JList.VERTICAL);
		//list.addListSelectionListener(this);	
		//list.setVisibleRowCount(5);
			
	}
	
	public void addElement(String element){
		Inventory_model.addElement(element);
	}
	
	public void removeElement(String element){
		Inventory_model.removeElement(element);
	}
	
	public JList getJList(){
		return list;
	}
	
	public boolean getItem_open() {
		return item_open;
	}
	
	public void setItem_open(boolean item_open) {
		this.item_open = item_open;
	}

}
