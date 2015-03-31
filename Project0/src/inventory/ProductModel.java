package inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JList;

public class ProductModel {
	
	private Gateway pdg;
	private HashMap<Integer, Product> products;//Key = id, V = the product
	private ArrayList<Integer> productIds;
	private ArrayList<String> productNumbers;
	private JList productList;
	Session session;
	
	private ListObserver o2;
	
	public ProductModel(Gateway pdg, Session session) {
		this.pdg = pdg;
		this.session = session;
		productIds = new ArrayList<Integer>();
		productNumbers = new ArrayList<String>();
		
		load();
		
	}

	public void registerObserver(ListObserver o2) {
		this.o2 = o2;
		
	}
	
	public JList getProductList() {
		return productList;
	}
	
	public void addProduct(Product product) {
		pdg.addProduct(product);
		update();
	}
	
	public void addProductPart(Product product, Part part) {
		pdg.addProductPart(product, part);
	}
	
	public void removeProduct(Product product) {
		pdg.removeProduct(product.getId());
		update();
	}
	
	public void removeProductPart(Product product, Part part) {
		pdg.removeProductPart(product, part);
	}
	
	public ArrayList<Part> getAssociatedParts(int id) {
		return pdg.getProductParts(id);
	}
	
	
	public Product getProductAt(int index) {
		int id = productIds.get(index);
		if(products.get(id) == null) {
			throw new IllegalArgumentException();
		} else {
			return products.get(id);
		}
	}
	
	public boolean checkPart(String partNum) {
		return pdg.checkPart(partNum);
	}
	
	public Part getPart(String partNum) {
		return pdg.getPart(partNum);
	}
	
	public Session getLoggedInUser() {
		return session;
	}
	
	public void refresh() {
		pdg.loadProducts();
		products = (HashMap<Integer, Product>) pdg.getProducts();		
	}
	
	private void load(){
		refresh();
		productIds.clear();
		productNumbers.clear();
		for(Product product : products.values()) {
			productIds.add(product.getId());
			productNumbers.add(product.getNumber());
		}
		productList = new JList(productNumbers.toArray());
	}
	
	private void update() {
		load();
		o2.update();
	}

}