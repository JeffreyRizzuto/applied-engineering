package inventory;

import java.util.ArrayList;
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
	
	public void removeProduct(Product product) {
		pdg.removeProduct(product.getId());
		update();
	}
	
	public HashMap<Integer, Part> getAssociatedParts(int id) {
		return pdg.getAssociatedParts(id);
	}
	
	
	public Product getProductAt(int index) {
		int id = productIds.get(index);
		if(products.get(id) == null) {
			throw new IllegalArgumentException();
		} else {
			return products.get(id);
		}
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
		refresh();
		productIds.clear();
		productNumbers.clear();
		load();
		o2.update();
	}

}