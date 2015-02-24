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

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class InventoryModel{

	private HashMap<Integer, Part> parts;//Key = partId, V = the part object
	private HashMap<Integer, Part> items;//Key = partId, V = the part object
	private JList<String> list;
	private InventoryGateway pdg;
	
	private int currentPartId = 1;
	private int currentItemId = 1;
	
	public InventoryModel(){		
		parts = new HashMap<Integer, Part>();
		items = new HashMap<Integer, Part>();
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		/* this is where we would add saved items if we used persistent data*/
		refresh();

	}
	
	public void close() {
		if(pdg != null)
			pdg.close();
	}
	
	public void refresh() {
		pdg.loadParts();
		parts = (HashMap<Integer, Part>) pdg.getParts();
		pdg.loadItems();
		items = (HashMap<Integer, Part>) pdg.getItems();
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
	
	public Part getElement(String item){
		if(item == null || parts.get(item) == null) {
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
		if(parts.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkItemIdExists(int id){
		if(items.get(id) != null) {
			return true;
		} else {
			return false;
		}
		
	}

	public JList<String> getItemList(){
		return list;
	}
	
	private void update(){
		list.setListData(new Vector<String>());
	}

}
