package Inventory;

public class Item {
	
	private String partNumber;
	private String partName;
	private String vendor;
	private int quantity;//java doesn't have a way to do unsigned right?
	
	public Item() {
		
	}
	
	public void setPartNumber(String partNumber){
		if(partNumber.length() < 20 && !partNumber.isEmpty()){
			this.partNumber = partNumber;
		}
		
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
