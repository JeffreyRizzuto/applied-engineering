/*
Id: automatically generated unique id
Part: the part at that Location (should be a link to an instance of Part)
Location: (same as before)
Quantity: (same as before)
Constraint: No two Inventory Items can have same Part/Location
*/
package inventory;

public class Item {
	
	private int id;
	private int part;
	private String unitLocation;
	private int quantity;
	//private String unitType;
	
	
	public Item() {
		
	}
	
	public void setId(int itemId){
		this.id = itemId;		
	}
	
	public void setPart(int part){
		this.part = part;
	}

	
	public void setUnitLocation(String location){
		if(location.equals("Unknown")){
			//Error may need to be thrown here
		}
		this.unitLocation = location;
	}
	
	
	public void setQuantity(int quantity){
		if(quantity >= 0) {
			this.quantity = quantity;
		}
	
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPart(){
		return this.part;
	}
	
	public String getUnitLocation(){
		return this.unitLocation;
	}
	
	
	public int getQuantity(){
		return this.quantity;
	}
	
	
	
}
