package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.Part;

public class EditTests {
	/*Editing a part involves just resetting the parts (Item) values. So we only
	 * need to check that a new set clears old values.
	 */
	
	Part part;


	@Before
	public void setUp() throws Exception {
		part = new Part();
		//Set everything to good values
		part.setPartName("oldPart");
		part.setPartNumber("oldItem");
		part.setQuantity(4);
		part.setVendor("oldVendor");
	}


	@Test
	public void testEditPartName() {
		part.setPartName("newPart");
		assertEquals("newPart", part.getPartName());
	}
	@Test
	public void testEditPartNumber() {
		part.setPartNumber("5");
		assertEquals("5", part.getPartNumber());
	}
	@Test
	public void testEditPartQuantity() {
		part.setQuantity(15);
		assertEquals(15, part.getQuantity());
	}


}
