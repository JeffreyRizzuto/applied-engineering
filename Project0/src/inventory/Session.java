package inventory;

import javax.management.relation.RoleStatus;

public class Session {
	boolean canViewProductTemplates;
	boolean canAddProductTemplates;
	boolean canDeleteProductTemplates;
	boolean canCreateProducts;
	boolean canViewInventory;
	boolean canAddInventory;
	boolean canViewParts;
	boolean canAddParts;
	boolean canDeleteParts;
	boolean canDeleteInventory;
	User user;
	String role;

	// Inventory Manager, Production Manager, and Admin

	public Session(User user, String role) {
		this.user = user;
		this.role = role;
		setAccesslevel();
	}

	private void setAccesslevel() {
		switch (role) {
		case "Inventory Manager":
			setIM();
			break;
		case "Production Manager":
			setPM();
			break;
		case "Admin":
			setAdmin();
			break;
		default:
			break;
		}
	}

	private void setIM() {
		canViewInventory = true;
		canAddInventory = true;
		canViewParts = true;
		canAddParts = true;
	}

	private void setPM() {
		canViewProductTemplates = true;
		canAddProductTemplates = true;
		canDeleteProductTemplates = true;
		canCreateProducts = true;
		canViewInventory = true;
		canViewParts = true;

	}

	private void setAdmin() {
		boolean canViewProductTemplates = true;
		boolean canAddProductTemplates = true;
		boolean canDeleteProductTemplates = true;
		boolean canCreateProducts = true;
		boolean canViewInventory = true;
		boolean canAddInventory = true;
		boolean canViewParts = true;
		boolean canAddParts = true;
		boolean canDeleteParts = true;
		boolean canDeleteInventory = true;
	}
	
	/*public void changeRole(String role) {
		
	}*/
}
