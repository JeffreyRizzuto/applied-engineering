package Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
public class Inventory_model{

	private Map<String, Item> items;
	private JList<String> list;
	
	public Inventory_model(){
		
		items = new HashMap<String, Item>();
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		/* this is where we would add saved items if we used persistent data*/
		//update();

	}
	
	public void addElement(Item item){
		if(items.get(item.getPartName()) != null){
			//throw error back to item_controller
		} else {
			System.out.println("adding: "+ item.getPartName());
			items.put(item.getPartName(),item);
			update();//update the JList to reflect changes
		}
	}
	
	public void removeElement(String item){
		if(items.get(item) == null){
			//throw error back to item_controller
		} else {
			items.remove(item);
			update();//update the JList to reflect changes
		}
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
