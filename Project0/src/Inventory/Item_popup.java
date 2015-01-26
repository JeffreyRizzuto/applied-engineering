package Inventory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;


public class Item_popup extends JFrame{
	
	private static final long serialVersionUID = -8616906922402009596L;
	
	private Inventory_model model;
	
	private final static String[] labels = {"Part Number: ", "Part Name: ", "Vendor: ", "Quantity: "};

	private int labelsLength = labels.length;
	private JPanel form;
	private Item_controller itemController;
	private JTextField partName;
	private JTextField partNumber;
	private JTextField vendor;
	private JTextField quantity;
	
	public Item_popup(Inventory_model model) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);

		JPanel form = new JPanel(new SpringLayout());
		itemController = new Item_controller(model,this);
		
		//here are the text fields for the form
		
		partName = new JTextField(10);
		partNumber = new JTextField(255);
		vendor = new JTextField(255);
		quantity = new JTextField(255);
		
		
		//add some labels to the fields (this is probably very ugly
		JLabel l;
		
		l = new JLabel("Part Number: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partNumber);
		form.add(partNumber);
		
		l = new JLabel("Part Name: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partName);
		form.add(partName);
		
		l = new JLabel("Vendor: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(vendor);
		form.add(vendor);
		
		l = new JLabel("Quantity: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(quantity);
		form.add(quantity);
		
		
		

		//set the layout for form with springUtilities (provided by oracle :P)
		SpringUtilities.makeCompactGrid(form,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		 
		 //make some buttons also
		 JPanel buttons = new JPanel(new SpringLayout());
		 JButton Submit = new JButton("Submit"); 
		 JButton Cancel = new JButton("Cancel");
		 
		 //add controllers
		 Submit.addActionListener(itemController);
		 Cancel.addActionListener(itemController);
		 
		 //add buttons to form
		 buttons.add(new JSeparator(SwingConstants.VERTICAL));
		 buttons.add(Submit);
		 buttons.add(Cancel);
		 
		 //set buttons layout
		 SpringUtilities.makeCompactGrid(buttons,
                 1, 3, 		 //rows, columns
                 6, 6,        //initX, initY
                 6, 6);       //xPad, yPad
		 
		//add form and buttons to the jframe
	    add(form, BorderLayout.CENTER);
	    add(buttons, BorderLayout.PAGE_END);
	
	}
	
	public void closeWindow(){
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));//close the window
	}
		
	public String getPartNumber(){
		return partNumber.getText();	
	}
	
	public String getPartName(){
		return partName.getText();	
	}
	
	public String getVendor(){
		return vendor.getText();	
	}
	
	public int getQuantity(){
		String value = quantity.getText();
		return !value.isEmpty() ? Integer.parseUnsignedInt(value) : 0;		
	}
}
