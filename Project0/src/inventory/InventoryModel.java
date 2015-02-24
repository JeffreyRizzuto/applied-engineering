/*
 * Possible future improvement of using a map instead
 * of a list to store item id's as maps have to have unique
 * elements, instead of forcing unique elements with a list.
 * 
 * Didn't think of that at the time of implementation :(
 * 
 */


package inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class InventoryModel{

	private HashMap<Integer, Part> parts;//Key = partId, V = the part object
	private HashMap<Integer, Item> items;//Key = partId, V = the part object
	private JList itemsList;
	private JList partsList;
	private ArrayList<String> partNameList;
	private ArrayList<String> itemNameList;
	private InventoryGateway pdg;
	
	
	private int currentPartId = 1;
	private int currentItemId = 1;
	
	public InventoryModel(){		
		parts = new HashMap<Integer, Part>();
		items = new HashMap<Integer, Item>();
		itemsList = new JList();
		partsList = new JList();
		
		partNameList = new ArrayList<String>();
		itemNameList = new ArrayList<String>();
		
		partsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		partsList.setLayoutOrientation(JList.VERTICAL);
		
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsList.setLayoutOrientation(JList.VERTICAL);
		
		/* this is where we would add saved items if we used persistent data*/
		refresh();
		getPartslist();
		getItemslist();

	}
	
	public void close() {
		if(pdg != null)
			pdg.close();
	}
	
	public void refresh() {
		pdg.loadParts();
		parts = (HashMap<Integer, Part>) pdg.getParts();
		pdg.loadItems();
		items = (HashMap<Integer, Item>) pdg.getItems();
	}
	
	public void addElement(Part part){
			parts.put(part.getId(),part);
			update();//update the JList to reflect changes
		
	}
	
	public void removeElement(String item){
		if(item == null || parts.get(item) == null){
			throw new IllegalArgumentException();
		} else {
			parts.remove(item);
			update();//update the JList to reflect changes
		}
	}
	
	public Part getElement(int item){
		if(parts.get(item) == null) {
			throw new IllegalArgumentException();
		} else {
			return parts.get(item);
		}
	}
	
	public boolean checkPartNumber(String partNumber) {
		if(parts.get(partNumber) == null){
			return false;
		}
		return true;
	}
	
	public boolean checkPartName(String item) {
		for(Part value : parts.values()) {
			if( item.equals(value.getPartName()) ){
				return true;
			}
		}
		return false;
	}
	
	public int getCurrentOpenPartId(){
		while(parts.get(currentPartId) != null) {
			currentPartId++;
		}
		return currentPartId;
			
		
	}
	
	public int getCurrentOpenItemId(){
		while(items.get(currentItemId) != null) {
			currentItemId++;
		}
		return currentItemId;
	}
	
	public boolean checkPartIdExists(int id){
		return parts.containsKey(id);
	}
	public boolean checkItemIdExists(int id){
		return items.containsKey(id);
	}
	
	private void getPartslist(){
		for(Part value : parts.values()) {
			String Name = value.getPartName();
			partNameList.add(Name);
		}
		
	}
	
	private void getItemslist(){
		for(Item value : items.values()) {
			Part itemPart = getElement(value.getPart());
			itemNameList.add(itemPart.getPartName());
		}
	}
	
	private void update(){
		partsList.setListData(new Vector<String>(partNameList));
		itemsList.setListData(new Vector<String>(itemNameList));
	}

}
