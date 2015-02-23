package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.InventoryModel;
import Inventory.Part;

/* Class runs test on the InventoryModel class to check for add portion as required by assignment*/
public class AddTests {
	
	private InventoryModel model;
	private Part expected;
	private Part returned;
	

	@Before
	public void setUp() throws Exception {
		 expected = new Part();
		 returned = new Part();
		 model = new InventoryModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGoodAdd() {
		expected.setPartNumber("dfjdkfkd");
		expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		model.addElement(expected);
		returned = model.getElement("hinge");
		
		assertEquals(expected, returned);
	}
	@Test
	public void testMissingPartName() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		//expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		try{
			model.addElement(expected);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
	}
	@Test
	public void testMissingPartNumber() {
		boolean thrown = false;
		//expected.setPartNumber("dfjdkfkd");
		expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		try{
			model.addElement(expected);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	@Test
	public void testMissingQuantity() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		expected.setPartName("hinge");
		//expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		try{
			model.addElement(expected);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	@Test
	public void testSameName() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		Part samepart = new Part();
		
		samepart.setPartNumber("dfjdkfkd");
		samepart.setPartName("hinge");
		samepart.setQuantity(4);
		samepart.setVendor("aVendor");
		
		model.addElement(expected);
		
		try{
			model.addElement(samepart);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	@Test
	public void testLongPartName() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		expected.setPartName("12345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890"
				+ "123456789012345678901234567890123456789012345678901234567890");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		
		try{
			model.addElement(expected);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	@Test
	public void testLongPartNumber() {
		boolean thrown = false;
		expected.setPartNumber("123456789012345678901");
		expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		
		
		try{
			model.addElement(expected);
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

}
