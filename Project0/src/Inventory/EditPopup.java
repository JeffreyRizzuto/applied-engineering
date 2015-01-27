package Inventory;

import java.awt.BorderLayout;
import java.awt.Color;
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


public class EditPopup extends JFrame{
	
	private static final long serialVersionUID = -8616906922402009596L;
	
	private InventoryModel model;
	
	private final static String[] labels = {"Part Number: ", "Part Name: ", "Vendor: ", "Quantity: "};

	private int labelsLength = labels.length;
	private JPanel form;
	private EditPopupController editController;
	private JTextField partName;
	private JTextField partNumber;
	private JTextField vendor;
	private JTextField quantity;
	private	Item selectedItem;
	
	public EditPopup(InventoryModel model, Item item) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);

		this.selectedItem = item;
		JPanel form = new JPanel(new SpringLayout());
		editController = new EditPopupController(model,this);
		
		//here are the text fields for the form
		partNumber = new JTextField(20);
		partName = new JTextField(255);
		vendor = new JTextField(255);
		quantity = new JTextField(255);
		
		//fill the textfields with the items info
		partNumber.setText(selectedItem.getPartNumber());
		partName.setText(selectedItem.getPartName());
		vendor.setText(selectedItem.getVendor());
		quantity.setText( Integer.toString(selectedItem.getQuantity()) );
		
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
		 Submit.addActionListener(editController);
		 Cancel.addActionListener(editController);
		 
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
	
	public Item getSelectedItem(){
		return selectedItem;
	}
	
	public void formatError(int errorCode){
		switch(errorCode){
		case 1:	errorCode=1;
			partName.setBackground(Color.red);
			break;
		case 2:	errorCode=2;
			partNumber.setBackground(Color.red);
			break;
		case 3:	errorCode=3;
			vendor.setBackground(Color.red);
			break;
		case 4:	errorCode=4;
			quantity.setBackground(Color.red);
			break;
		}
	}
	
	public void resetErrors(){
		partName.setBackground(Color.WHITE);
		partNumber.setBackground(Color.WHITE);
		vendor.setBackground(Color.WHITE);
		quantity.setBackground(Color.WHITE);
	}
}
