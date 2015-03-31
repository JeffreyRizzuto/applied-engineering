/*
 * Possible future improvement of using a map instead
 * of a list to store item id's as maps have to have unique
 * elements, instead of forcing unique elements with a list.
 * 
 * Didn't think of that at the time of implementation :(
 * 
 */

/* this is old code from the start of the project, when I kinda of hacked it together
 * to make it work, eventually I want to update this code with the new format of the controller
 * like productModel
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

public class InventoryModel {

	private HashMap<Integer, Part> parts;// Key = partId, V = the part object
	private HashMap<Integer, Item> items;// Key = partId, V = the part object
	private DefaultListModel<String> partList;
	private DefaultListModel<String> itemList;
	private DefaultListModel<Integer> partIdList;
	private DefaultListModel<Integer> itemIdList;
	private Gateway pdg;
	Session session;

	private ListObserver o1;

	public InventoryModel(Gateway pdg, Session session) {
		this.pdg = pdg;
		this.session = session;
		partList = new DefaultListModel();
		itemList = new DefaultListModel();
		partIdList = new DefaultListModel();
		itemIdList = new DefaultListModel();
		/* this is where we would add saved items if we used persistent data */
		load();

	}

	public void close() {
		if (pdg != null)
			pdg.close();
	}

	public void refresh() {
		pdg.loadParts();
		parts = (HashMap<Integer, Part>) pdg.getParts();
		pdg.loadItems();
		items = (HashMap<Integer, Item>) pdg.getItems();
	}

	public void addPart(Part part) {
		parts.put(part.getId(), part);
		pdg.addPart(part);
		update();// update the JList to reflect changes

	}

	public void addItem(Item item) {
		items.put(item.getId(), item);
		pdg.addItem(item);
		update();// update the JList to reflect changes

	}

	public void removePart(int item) {
		if (parts.get(item) == null) {
			throw new IllegalArgumentException();
		} else {
			pdg.removePart(item);
			update();// update the JList to reflect changes
		}
	}

	public void removeitem(int item) {
		if (items.get(item) == null) {
			throw new IllegalArgumentException();
		} else {
			pdg.removeItem(item);
			items.remove(item);
			
			update();// update the JList to reflect changes
		}
	}

	public Part getPart(int partNum) {
		if (parts.get(partNum) == null) {
			return null;
		} else {
			return parts.get(partNum);
		}
	}

	public Item getItem(int id) {
		return items.get(id);
	}

	public boolean checkPartNumber(String partNumber) {
		for (Part value : parts.values()) {
			if (partNumber.equals(value.getPartNumber())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPartName(String item) {
		for (Part value : parts.values()) {
			if (item.equals(value.getPartName())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPartAssociation(int id) {
		return pdg.checkPartAssociation(id);
	}

	public boolean checkPartIdExists(int id) {
		return parts.containsKey(id);
	}

	public boolean checkItemIdExists(int id) {
		return items.containsKey(id);
	}

	public JList getPartsList() {
		return new JList(partList);
	}

	public JList getItemsList() {
		return new JList(itemList);
	}
	
	public Part getPartByName(String name) {
		for (Part p : parts.values()) {
			if(p.getPartName().equals(name))
				return p;
		}
		return null;
	}
	
	public Item getItemByName(int name) {
		
		int id = itemIdList.get(name);
		for (Item i : items.values()) {
			if(i.getId() == id)
				return i;
		}
		return null;
	}


	public void registerObserver(ListObserver o1) {
		this.o1 = o1;

	}
	
	//get user
	public Session getLoggedInUser() {
		return session;
	}

	// functions for the lock
	// might try and combine these 3 functions

	public boolean checkLock(int id) {
		return pdg.checkLock(id);
	}

	public void lockPart(int id) {
		pdg.lockPart(id);
	}

	public void unlockPart(int id) {
		pdg.unlockPart(id);
	}

	private void load() {
		refresh();
		for (Part p : parts.values()) {
			partList.addElement(p.getPartName());
		}

		for (Item i : items.values()) {
			itemList.addElement(getPart(i.getPart()).getPartName());
			itemIdList.addElement(i.getId());
		}
	}

	private void update() {
		refresh();
		partList.clear();
		itemList.clear();
		itemIdList.clear();
		for (Part p : parts.values()) {
			partList.addElement(p.getPartName());
		}

		for (Item i : items.values()) {
			itemList.addElement(getPart(i.getPart()).getPartName());
			itemIdList.addElement(i.getId());
		}
		o1.update();
	}
}
