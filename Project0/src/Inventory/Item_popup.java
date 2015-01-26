package Inventory;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	private JTextField[] textField;
	
	public Item_popup(Inventory_model model) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);

		JPanel form = new JPanel(new SpringLayout());
		itemController = new Item_controller(model,this);
		
		for (int i = 0; i < labelsLength; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			form.add(l);
			textField[i] = new JTextField(255);
			l.setLabelFor(textField[i]);
			textField[i].addActionListener(itemController);
			form.add(textField[i]);
		}
		
		//set the layout for form with springUtilities (provided by oracle :P)
		 SpringUtilities.makeCompactGrid(form,
                 labelsLength, 2, //rows, columns
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
	
	public final static String[] getLabels(){
		return labels;
		
	}
	
	public JTextField[] getTextField(){
		return textField;
		
	}
}
