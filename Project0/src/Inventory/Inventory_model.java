package Inventory;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.text.Position;

public class Inventory_model{

	private Map<String, Item> items;
	private JList list;
	private DefaultListModel Inventory_model;
	
	public Inventory_model(){

		items = new HashMap();
		list = new JList();
		Inventory_model = new DefaultListModel();
		list.setModel(Inventory_model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
	}
	
	public void addElement(Item item){
		if(items.get(item.getPartName()) == null){
			//throw error back to item_controller
		} else {
			items.put(item.getPartName(),item);
		}
	}
	
	public void removeElement(Item item){
		if(items.get(item.getPartName()) == null){
			//throw error back to item_controller
		} else {
			items.put(item.getPartName(),item);
		}
	}
}
