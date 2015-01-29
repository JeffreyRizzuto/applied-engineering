package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inventory.InventoryModel;
import Inventory.Item;

public class AddTests {
	
	private InventoryModel model;
	private Item expected;
	private Item returned;
	

/*
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	*/

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
		model.addElement(expected);
		
		try{
		returned = model.getElement("hinge");
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
	}
	@Test
	public void testMissingPartNumber() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		//expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		model.addElement(expected);
		
		try{
		returned = model.getElement("hinge");
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	@Test
	public void testMissingQuantity() {
		boolean thrown = false;
		expected.setPartNumber("dfjdkfkd");
		//expected.setPartName("hinge");
		expected.setQuantity(4);
		expected.setVendor("aVendor");
		model.addElement(expected);
		
		try{
		returned = model.getElement("hinge");
		} catch(IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

}
