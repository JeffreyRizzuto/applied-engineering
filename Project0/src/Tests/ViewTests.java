package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.InventoryModel;
import Inventory.Item;

public class ViewTests {
	
	private InventoryModel model;
	Item expected;
	Item returned;
	


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 expected = new Item();
		 returned = new Item();
		 model = new InventoryModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGoodAdd() {
		expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		model.addElement(expected);
		returned = model.getElement("hinge");
		
		assertEquals(expected, returned);
	}
	

}
