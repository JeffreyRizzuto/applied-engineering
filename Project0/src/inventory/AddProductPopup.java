package inventory;

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


public class AddProductPopup extends JFrame{
	
	private static final long serialVersionUID = -8616906922402009596L;
	
	private ProductModel model;
	
	private JPanel form;
	private AddProductPopupController productController;
	
	//private JTextField partId;
	private JTextField productNumber;
	private JTextField productDescription;

	public AddProductPopup(ProductModel model) {

		super("Add New Item");
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);

		JPanel form = new JPanel(new SpringLayout());
		productController = new AddProductPopupController(model,this);
		
		//here are the text fields for the form
		//partId = new JTextField(10);
		productNumber = new JTextField(20);
		productDescription = new JTextField(255);
		
		
		//add some labels to the fields (this is probably very ugly
		JLabel l;
		/*
		l = new JLabel("Item ID: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(partId);
		form.add(partId);
		*/
		l = new JLabel("Product Number: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(productNumber);
		form.add(productNumber);
		
		l = new JLabel("Product Description: ", JLabel.TRAILING);
		form.add(l);
		l.setLabelFor(productDescription);
		form.add(productDescription);		
		
		

		//set the layout for form with springUtilities (provided by oracle :P)
		SpringUtilities.makeCompactGrid(form,
                2, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		 
		 //make some buttons also
		 JPanel buttons = new JPanel(new SpringLayout());
		 JButton Submit = new JButton("Submit"); 
		 JButton Cancel = new JButton("Cancel");
		 
		 //add controllers
		 Submit.addActionListener(productController);
		 Cancel.addActionListener(productController);
		 
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
	
	public String getNumber() {
		return productNumber.getText();
		
	}
	
	public String getDescription() {
		return productDescription.getText();
		
	}

	public void formatError(int errorCode){
		switch(errorCode){

		case 1:	errorCode=1;//part #
			productNumber.setBackground(Color.red);
			break;	
		case 2:	errorCode=2;//part name
			productDescription.setBackground(Color.red);
			break;
		}
	}
	
	public void resetErrors(){
		//partId.setBackground(Color.WHITE);
		productNumber.setBackground(Color.WHITE);
		productDescription.setBackground(Color.WHITE);
	}
}
