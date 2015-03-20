package inventory;

import javax.swing.JOptionPane;

public class DeletePopup extends JOptionPane{
	
	private static final long serialVersionUID = 3985454685567236619L;
	
	private boolean Answer;

	public DeletePopup(Object Selected) {

		setOptionType(YES_NO_OPTION);
		if(showConfirmDialog(this,"Are you sure you want to delete: " + Selected)==0){
			Answer = true;//yes
		}
		else {
			Answer = false;//no
		}
	}
	
	public boolean response(){
		return Answer;
	}

}
