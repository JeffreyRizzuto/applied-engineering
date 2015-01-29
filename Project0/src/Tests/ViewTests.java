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
	Item goodItem = new Item();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 model = new InventoryModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGoodAdd() {
		
		model.addElement(goodItem);
	}

}
