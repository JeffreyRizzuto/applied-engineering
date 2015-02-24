/*
 * Possible future improvement of using a map instead
 * of a list to store item id's as maps have to have unique
 * elements, instead of forcing unique elements with a list.
 * 
 * Didn't think of that at the time of implementation :(
 * 
 */


package Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
public class InventoryModel{

	private HashMap<String, Part> items;//Key = partId, V = the part object
	private JList<String> list;
	private int currentOpenId;
	private ArrayList<Integer> idList;
	
	public InventoryModel(){		
		items = new HashMap<String, Part>();
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		idList = new ArrayList<Integer>();
		/* this is where we would add saved items if we used persistent data*/
		//update();
		
		this.currentOpenId = 1;

	}
	
	public void addElement(Part part){
			items.put(part.getPartNumber(),part);
			addId(part.getId());//add item id to id list
			update();//update the JList to reflect changes
		
	}
	
	public void removeElement(String item){
		if(item == null || items.get(item) == null){
			throw new IllegalArgumentException();
		} else {
			removeId(items.get(item).getId());//remove the id from the list
			items.remove(item);
			update();//update the JList to reflect changes
		}
	}
	
	public Part getElement(String item){
		if(item == null || items.get(item) == null) {
			throw new IllegalArgumentException();
		} else {
			return items.get(item);
		}
	}
	
	public boolean checkPartNumber(String partNumber) {
		if(items.get(partNumber) == null){
			return false;
		}
		return true;
	}
	
	public boolean checkPartName(String item) {
		/*make an iterator to go through items, see if item exists with this name */
		/* lots of casting :( don't know how to do it otherwise */
		for(Part value : items.values()) {
			if( item.equals(value.getPartName()) ){
				return true;
			}
		}
		return false;
	}
	
	public int getCurrentOpenId(){
		currentOpenId++;
		return currentOpenId-1;
	}
	
	private void addId(int id){
		idList.add(new Integer(id));
	}
	
	private void removeId(int id){
		idList.remove(new Integer(id));
	}
	
	public boolean checkIdExists(int id){
		return(idList.contains(id));//returns true if id is taken
		
	}

	public JList<String> getItemList(){
		return list;
	}
	
	private void update(){
		list.setListData(new Vector<String>(items.keySet()));
	}

}
