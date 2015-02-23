/*
Id: automatically generated unique id
Part #: (same as before but now required and unique)
Part Name: (same as before but no longer unique)
Vendor: (same as before)
Unit of Quantity: (same as before)
External Part #: (same as before)
Constraint: Part # and Part Name are required. Vendor is not.
Constraint: No two Parts can have the same Part #
*/
package Inventory;

public class Part {
	private int id;
	private String partNumber;
	private String partName;
	private String vendor;
	private int quantity;//java doesn't have a way to do unsigned right?
	private String externalPartNumber;
	
	public Part() {
		
	}
	
	public void setExternalPartNumber(String partNumber){
		this.externalPartNumber = partNumber;
	}
	
	public void setPartNumber(String partNumber){
		if(partNumber.length() < 20 && !partNumber.isEmpty()){
			this.partNumber = partNumber;
		}
		
	}
	
	public void setId(int itemId){
		this.id = itemId;		
	}
	
	public void setPartName(String partName){
		if(partName.length() < 255 && !partName.isEmpty() ){
			this.partName = partName;
		}	
		
	}
	
	public void setVendor(String vendor){
		if( vendor.length() < 255 ||vendor.isEmpty()){
			this.vendor = vendor;
		}
		
	}
	
	public void setQuantity(int quantity){
		if(quantity >= 0) {
			this.quantity = quantity;
		}
	
	}
		
	public int getId() {
		return this.id;

	}

	public String getExternalPartNumber(){
		return externalPartNumber;
	}
	
	public String getPartNumber() {
		return this.partNumber;
		
	}
	
	public String getPartName() {
		return this.partName;
	}
	
	public String getVendor() {
		return this.vendor;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public String toString(){
		return this.partName;
	}
}
