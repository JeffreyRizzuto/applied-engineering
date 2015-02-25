/*
 * Possible future improvement of using a map instead
 * of a list to store item id's as maps have to have unique
 * elements, instead of forcing unique elements with a list.
 * 
 * Didn't think of that at the time of implementation :(
 * 
 */


package inventory;

import java.awt.List;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class InventoryModel{

	private HashMap<Integer, Part> parts;//Key = partId, V = the part object
	private HashMap<Integer, Item> items;//Key = partId, V = the part object
	private ArrayList<String> itemsListName;
	private ArrayList<String> partsListName;
	private ArrayList<Integer> itemsListId;
	private ArrayList<Integer> partsListId;
	private JList partList;
	private JList itemList;
	private InventoryGateway pdg;
	
	private ListObserver o1;
	
	
	private int currentPartId = 1;
	private int currentItemId = 1;
	

	public InventoryModel(InventoryGateway pdg){
		this.pdg = pdg;
		
		
				
		partsListName = new ArrayList<String>();
		itemsListName = new ArrayList<String>();
		partsListId = new ArrayList<Integer>();
		itemsListId = new ArrayList<Integer>();
		
		/* this is where we would add saved items if we used persistent data*/
		load();		

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
	
	public void addPart(Part part){
			parts.put(part.getId(),part);
			pdg.addPart(part);
			update();//update the JList to reflect changes
		
	}

	public void addItem(Item item){
			items.put(item.getId(),item);
			pdg.addItem(item);
			update();//update the JList to reflect changes
		
	}
	
	public void removePart(int item){
		if(parts.get(parts) == null){
			throw new IllegalArgumentException();
		} else {
			//remove from mysql
			parts.remove(item);
			update();//update the JList to reflect changes
		}
	}
	
	public void removeitem(int item){
		if(items.get(item) == null){
			throw new IllegalArgumentException();
		} else {
			//remove from mysql
			items.remove(item);
			
			update();//update the JList to reflect changes
		}
	}
	
	public Part getPart(int partNum){
		if(parts.get(partNum) == null) {
			throw new IllegalArgumentException();
		} else {
			return parts.get(partNum);
		}
	}
	
	public Item getItem(int itemNum){
		if(items.get(itemNum) == null) {
			throw new IllegalArgumentException();
		} else {
			return items.get(itemNum);
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
	
	public void populatePartsList(){
		for(Part part : parts.values()) {
			partsListName.add(part.getPartName());//so hacky
			partsListId.add(part.getId());
		}
	}
	
	public void populateItemsList(){
		for(Item item : items.values()) {
			itemsListName.add(getPart(item.getId()).getPartName());
			itemsListId.add(item.getId());
		}
	}
	
	public JList getPartsList() {
		return partList;
	}
	
	public JList getItemsList() {
		return itemList;
	}
	
	public int partToId(int index){
		return partsListId.get(index);
	}
	
	/*public int itemToId(int index){
		return items.get(itemsListId.get(index)).getPart();
	}*/
	
	public int itemToId(int index){
	return itemsListId.get(index);
	}
	
	public void addPartsList(String name, Integer id) {
		partsListName.add(name);
		partsListId.add(id);
	}
	
	public void addItemsList(String name, Integer id) {
		itemsListName.add(name);
		itemsListId.add(id);
	}
	
	public void registerObserver(ListObserver o1) {
		this.o1 = o1;
		
	}
	
	private void load(){
		refresh();
		partsListName.clear();
		itemsListName.clear();
		partsListId.clear();
		itemsListId.clear();
		populateItemsList();
		populatePartsList();
		partList = null;
		itemList = null;
		partList = new JList(partsListName.toArray());
		itemList = new JList(itemsListName.toArray());
	}
	
	private void update() {
		refresh();
		partsListName.clear();
		itemsListName.clear();
		partsListId.clear();
		itemsListId.clear();
		populateItemsList();
		populatePartsList();
		partList = new JList(partsListName.toArray());
		itemList = new JList(itemsListName.toArray());
		o1.update();
	}
}
