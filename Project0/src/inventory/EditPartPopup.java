package inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
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


public class EditPartPopup extends JFrame{
	
	private static final long serialVersionUID = -8616906922402009596L;
	
	private InventoryModel model;
	
	private final static String[] labels = {"Part Number: ", "Part Name: ", "Vendor: ", "Quantity: "};

	private int labelsLength = labels.length;
	private JPanel form;
	private EditPartPopupController editController;
	
	private JTextField partId;
	private JTextField partName;
	private JTextField partNumber;
	private JTextField vendor;
	private JComboBox unitType;
	private JTextField externalPartNumber;
	
	private	Part selectedItem;
	
	private String[] locations = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2"};
	private String[] unitTypes = {"Linear Feet", "Pieces"};
	
	public EditPartPopup(final InventoryModel model, Part item) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);

		this.selectedItem = item;
		JPanel form = new JPanel(new SpringLayout());
		editController = new EditPartPopupController(model,this);
		
		//here are the text fields for the form
		partId = new JTextField(255);
		partNumber = new JTextField(20);
		partName = new JTextField(255);
		vendor = new JTextField(255);
		unitType = new JComboBox();
		externalPartNumber = new JTextField(255);
		
		for(int i = 0; i < 2; i++){
			unitType.addItem(unitTypes[i]);
		}
		
		//fill the textfields with the items info
		//set default id for the id
		partId.setText( Integer.toString(selectedItem.getId()) );
		partNumber.setText(selectedItem.getPartNumber());
		partName.setText(selectedItem.getPartName());
		vendor.setText(selectedItem.getVendor());
		unitType.setSelectedItem(selectedItem.getUnitType());
		externalPartNumber.setText(selectedItem.getExternalPartNumber());
		
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
		
		
		l = new JLabel("Part Name: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partName);
		form.add(partName);
		
		l = new JLabel("Vendor: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(vendor);
		form.add(vendor);
				
		l = new JLabel("Unit Type: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(unitType);
		form.add(unitType);
		
		l = new JLabel("EXT Part Number: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(externalPartNumber);
		form.add(externalPartNumber);

		//set the layout for form with springUtilities (provided by oracle :P)
		SpringUtilities.makeCompactGrid(form,
                6, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		 
		 //make some buttons also
		 JPanel buttons = new JPanel(new SpringLayout());
		 JButton Submit = new JButton("Submit"); 
		 JButton Cancel = new JButton("Cancel");
		 
		 //add controllers
		 Submit.addActionListener(editController);
		 Cancel.addActionListener(editController);
		 //on close we need to free the lock
		 this.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                 model.unlockPart( selectedItem.getId() );
             }
         });
		 
		 //add buttons to form
		 buttons.add(new JSeparator(SwingConstants.VERTICAL));
		 buttons.add(Submit);
		 buttons.add(Cancel);
		 
		 //set buttons layout
		 SpringUtilities.makeCompactGrid(form,
	                5, 2, //rows, cols
	                6, 6,        //initX, initY
	                6, 6);       //xPad, yPad
		 
		//add form and buttons to the jframe
	    add(form, BorderLayout.CENTER);
	    add(buttons, BorderLayout.PAGE_END);
	
	}
	
	public void closeWindow(){
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));//close the window
	}
	
	public String getPartId(){
		return partId.getText();
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
	
	public String getUnitQuantity(){
		return (String) unitType.getSelectedItem();
	}
	
	public String getExternalPartNumber(){
		return externalPartNumber.getText();
	}
	
	public Part getSelectedItem(){
		return selectedItem;
	}
	
	
	/* if time, i want to remove this function and replace it with throw/catches */
	public void formatError(int errorCode){
		switch(errorCode){
		case 0:	errorCode=0;//id
			partId.setBackground(Color.red);
			break;
		case 1:	errorCode=1;//part #
			partNumber.setBackground(Color.red);
			break;	
		case 2:	errorCode=2;//part name
			partName.setBackground(Color.red);
			break;
		case 3:	errorCode=3;//vendor
			vendor.setBackground(Color.red);
			break;
		case 4:	errorCode=4;//unit of quantity
			//quantity.setBackground(Color.red);
			//break;
		case 5:	errorCode=5;//external part #
			//wish i could set background to combo box easy
			break;
		}
	}
	
	public void resetErrors(){
		partId.setBackground(Color.WHITE);
		partName.setBackground(Color.WHITE);
		partNumber.setBackground(Color.WHITE);
		vendor.setBackground(Color.WHITE);
	}

}
