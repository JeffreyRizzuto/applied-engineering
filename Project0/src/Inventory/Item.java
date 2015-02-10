package Inventory;

public class Item {
	
	private String partNumber;
	private String partName;
	private String vendor;
	private int id;
	private int quantity = -1000;//java doesn't have a way to do unsigned right?
	private String unitType;
	private String unitLocation;
	
	public Item() {
		
	}
	
	public void setUnitType(String unit){
		if(unit.equals("Unknown")){
			//Error may need to be thrown here
		}
		this.unitType = unit;
	}
	
	public void setUnitLocation(String location){
		if(location.equals("Unknown")){
			//Error may need to be thrown here
		}
		this.unitLocation = location;
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
	
	public String getUnitLocation(){
		return this.unitLocation;
	}
	
	public String getUnitType(){
		return this.unitType;
	}
	
	public int getId() {
		return this.id;

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
