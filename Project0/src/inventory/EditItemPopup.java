/* MODIFY THIS */

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


public class EditItemPopup extends JFrame{
	
	private static final long serialVersionUID = -8616906922402009596L;
	
	private InventoryModel model;
	
	private final static String[] labels = {"Part ID: ", "Part: ", "Location: ", "Quantity: "};

	private int labelsLength = labels.length;
	private JPanel form;
	private EditItemPopupController editController;
	private JTextField part; //He just called this part?
	private JTextField quantity;
	private JComboBox unitLocation;
	private	Item selectedItem;
	private JTextField id;
	private String[] locations = {"Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2"};
	
	public EditItemPopup(InventoryModel model, Item item) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);

		this.selectedItem = item;
		JPanel form = new JPanel(new SpringLayout());
		editController = new EditItemPopupController(model,this);
		
		//here are the text fields for the form
		id = new JTextField(55);
		part = new JTextField(255);
		quantity = new JTextField(255);
		unitLocation = new JComboBox();
		
		for(int i = 0; i < 3; i++){
			unitLocation.addItem(locations[i]);
		}
		
		//set text
		id.setText(Integer.toString(item.getId()));
		part.setText(Integer.toString(item.getPart()));
		quantity.setText(Integer.toString(selectedItem.getQuantity()));
		unitLocation.setSelectedItem(selectedItem.getUnitLocation());
		
		//add some labels to the fields (this is probably very ugly
		JLabel l;
		
		l = new JLabel("ID: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(id);
		id.setEditable(false);
		form.add(id);
		
		//again not sure what he wants here
		l = new JLabel("Part : ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(part);
		form.add(part);
		
		
		l = new JLabel("Quantity: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(quantity);
		form.add(quantity);

		
		l = new JLabel("Location: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(unitLocation);
		form.add(unitLocation);
		
		

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
	
	public String getId() {
		return id.getText();
	}

	public String getPartID(){
		return part.getText();
	}
	
	
	public String getQuantity(){
		//String value = quantity.getText();
		//return !value.isEmpty() ? Integer.parseUnsignedInt(value) : 0;
		return quantity.getText();
	}
	
	
	public Item getSelectedItem(){
		return selectedItem;
	}
	
	public String getUnitLocation(){
		return (String) unitLocation.getSelectedItem();
	}
	
	public void formatError(int errorCode){
		switch(errorCode){
		//Will need to modify this if we wish to stick with error codes
		case 1:	errorCode=1;
			quantity.setBackground(Color.red);
			break;
		case 2:	errorCode=2;
			id.setBackground(Color.red);
			break;
		case 3:	errorCode=3;
			part.setBackground(Color.red);
			break;
		}
	}
	
	public void resetErrors(){
		//Will need to add to this if we stick with error codes
		quantity.setBackground(Color.WHITE);
	}
}
