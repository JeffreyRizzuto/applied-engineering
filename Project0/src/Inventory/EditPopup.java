package Inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JTextField externalPartNumber;
	private JTextField vendor;
	private JTextField quantity;
	private JComboBox unitType;
	private JComboBox unitLocation;
	private	Part selectedItem;
	private JTextField partId;
	private String[] locations = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2"};
	private String[] unitTypes = {"Linear Feet", "Pieces"};
	
	public EditPopup(InventoryModel model, Part item) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);

		this.selectedItem = item;
		JPanel form = new JPanel(new SpringLayout());
		editController = new EditPopupController(model,this);
		
		//here are the text fields for the form
		partNumber = new JTextField(20);
		externalPartNumber = new JTextField(255);
		partName = new JTextField(255);
		vendor = new JTextField(255);
		quantity = new JTextField(255);
		partId = new JTextField(255);
		unitType = new JComboBox();
		unitLocation = new JComboBox();
		
		for(int i = 0; i < 2; i++){
			unitType.addItem(unitTypes[i]);
		}
		for(int i = 0; i < 3; i++){
			unitLocation.addItem(locations[i]);
		}
		
		//fill the textfields with the items info
		//set default id for the id
		partId.setText( Integer.toString(selectedItem.getId()) );
		partNumber.setText(selectedItem.getPartNumber());
		partName.setText(selectedItem.getPartName());
		vendor.setText(selectedItem.getVendor());
		quantity.setText( Integer.toString(selectedItem.getQuantity()) );
		unitType.setSelectedItem(selectedItem.getUnitType());
		externalPartNumber.setText(selectedItem.getExternalPartNumber());
		unitLocation.setSelectedItem(selectedItem.getUnitLocation());
		
		//add some labels to the fields (this is probably very ugly
		JLabel l;
		
		l = new JLabel("Item ID: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partId);
		partId.setEditable(false);
		form.add(partId);
		
		
		l = new JLabel("Part Number: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partNumber);
		form.add(partNumber);
		
		l = new JLabel("EXT Part Number: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(externalPartNumber);
		form.add(externalPartNumber);
		
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
		
		l = new JLabel("Unit Type: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(unitType);
		form.add(unitType);
		
		l = new JLabel("Location: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(unitLocation);
		form.add(unitLocation);
		
		

		//set the layout for form with springUtilities (provided by oracle :P)
		SpringUtilities.makeCompactGrid(form,
                8, 2, //rows, cols
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
	
	public String getExternalPartNumber(){
		return externalPartNumber.getText();
	}
	
	public String getPartName(){
		return partName.getText();	
	}
	
	public String getVendor(){
		return vendor.getText();	
	}
	
	public String getQuantity(){
		//String value = quantity.getText();
		//return !value.isEmpty() ? Integer.parseUnsignedInt(value) : 0;
		return quantity.getText();
	}
	
	public String getUnitType(){
		return (String) unitType.getSelectedItem();
	}
	
	public Part getSelectedItem(){
		return selectedItem;
	}
	
	public String getUnitLocation(){
		return (String) unitLocation.getSelectedItem();
	}
	
	/* if time, i want to remove this function and replace it with throw/catches */
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
		case 6:	errorCode=6;
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
