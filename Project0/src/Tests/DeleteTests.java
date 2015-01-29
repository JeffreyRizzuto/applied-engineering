package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.InventoryModel;
import Inventory.Item;

public class DeleteTests {
	
	private InventoryModel model;
	private Item part;

	@Before
	public void setUp() throws Exception {
		model = new InventoryModel();
		part = new Item();
		part.setPartNumber("1010");
		part.setPartName("hinge");
		part.setQuantity(4);
		part.setVendor("aVendor");
		model.addElement(part);
	}



	@Test
	public void testSuccessfulDelete() {
		model.removeElement("hinge");
		assertFalse(model.checkElement("hinge"));
	}
	@Test
	public void testItemNotFound() {
		
		boolean thrown = false;
		try{
			model.removeElement("DontHaveIt");
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	@Test
	public void testItemNull() {
		
		boolean thrown = false;
		try{
			model.removeElement(null);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

}
