package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.InventoryModel;
import Inventory.Part;


/*Not really much to test for for view Parts. Weve testing adding valid/invalid values
 * so really we just need to make sure the model can return proper parts with a few
 * differences like empty, one and a few parts
 */
public class ViewTests {
	
	private InventoryModel model;
	Part item1 = new Part();
	Part item2 = new Part();
	Part item3 = new Part();
	
	InventoryModel modelEmpty = new InventoryModel();
	InventoryModel modelOne = new InventoryModel();
	InventoryModel modelFew = new InventoryModel();
	


	@Before
	public void setUp() throws Exception {

		item1.setPartName("part1");
		item1.setPartNumber("p1");
		item1.setQuantity(1);
		item1.setVendor("Vendor1");
		
		item2.setPartName("part2");
		item2.setPartNumber("p2");
		item2.setQuantity(2);
		item2.setVendor("Vendor2");
		
		item3.setPartName("part3");
		item3.setPartNumber("p3");
		item3.setQuantity(3);
		item3.setVendor("Vendor3");
		
		modelOne.addElement(item1);
		modelFew.addElement(item1);
		modelFew.addElement(item2);
		modelFew.addElement(item3);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNoElements() {
		assertEquals(null, modelEmpty.getElement("part1"));

	}
	@Test
	public void testOneElement() {
		assertEquals(item1, modelOne.getElement("part1"));

	}
	@Test
	public void testManyElements() {
		assertEquals(item1, modelFew.getElement("part1"));
		assertEquals(item2, modelFew.getElement("part2"));
		assertEquals(item3, modelFew.getElement("part3"));

	}
	

}
