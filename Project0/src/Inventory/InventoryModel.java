package Inventory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
public class InventoryModel{

	private SortedMap<String, Item> items;
	private JList<String> list;
	
	public InventoryModel(){
		
		items = new TreeMap<String, Item>();
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		/* this is where we would add saved items if we used persistent data*/
		//update();

	}
	
	public void addElement(Item item){
		if(item.getPartName()==null || item.getPartNumber()==null || item.getQuantity()<0){
			throw new IllegalArgumentException();
		}
			items.put(item.getPartName(),item);
			update();//update the JList to reflect changes
		
	}
	
	public void removeElement(String item){
		if(items.get(item) == null){
			//throw error back to item_controller
		} else {
			items.remove(item);
			update();//update the JList to reflect changes
		}
	}
	
	public Item getElement(String item){
		if(items.get(item) == null){
			//throw error back to item_controller
		} else {
			return items.get(item);
		}
		return null;
	}
	
	public boolean checkElement(String item){
		if(items.get(item) == null){
			return false;
		} else {
			return true;
		}
	}

	public JList<String> getItemList(){
		return list;
	}
	
	private void update(){
		list.setListData(new Vector<String>(items.keySet()));
	}

}
