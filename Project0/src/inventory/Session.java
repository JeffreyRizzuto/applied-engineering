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
	
	public String getRole() {
		return role;
	}

	private void setIM() {
		canViewProductTemplates = false;
		canAddProductTemplates = false;
		canDeleteProductTemplates = false;
		canCreateProducts = false;
		canViewInventory = true;
		canAddInventory = true;
		canViewParts = true;
		canAddParts = true;
		canDeleteParts = false;
		canDeleteInventory = false;
	}

	private void setPM() {
		canViewProductTemplates = true;
		canAddProductTemplates = true;
		canDeleteProductTemplates = true;
		canCreateProducts = true;
		canViewInventory = true;
		canAddInventory = false;
		canViewParts = true;
		canAddParts = false;
		canDeleteParts = false;
		canDeleteInventory = false;

	}

	private void setAdmin() {
		canViewProductTemplates = true;
		canAddProductTemplates = true;
		canDeleteProductTemplates = true;
		canCreateProducts = true;
		canViewInventory = true;
		canAddInventory = true;
		canViewParts = true;
		canAddParts = true;
		canDeleteParts = true;
		canDeleteInventory = true;
	}
	
	public boolean canViewProductTemplates() {
		return canViewProductTemplates;
	}
	public boolean canAddProductTemplates() {
		return canAddProductTemplates;
	}
	public boolean canDeleteProductTemplates() {
		return canDeleteProductTemplates;
	}
	public boolean canCreateProducts() {
		return canCreateProducts;
	}
	public boolean canViewInventory() {
		return canViewInventory;
	}
	public boolean canAddInventory() {
		return canAddInventory;
	}
	public boolean canViewParts() {
		return canViewParts;
	}
	public boolean canAddParts() {
		return canAddParts;
	}
	public boolean canDeleteParts() {
		return canDeleteParts;
	}
	public boolean canDeleteInventory() {
		return canDeleteInventory;
	}
		
	
	
	
	/*public void changeRole(String role) {
		
	}*/
}
