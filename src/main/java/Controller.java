package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
	
	private OntologyController ontologyController = new OntologyController();
    
    
    public Controller(){
    	ontologyController.init();
    }
    
    
    public ArrayList<String> getAllProducts() {
    	ArrayList<String> products=new ArrayList<String>();
    	ontologyController.getAllProducts();
    	return ontologyController.getAllProducts();
    }
    public ArrayList<String> getProductsOfClass(String className) {
    	return ontologyController.getInstance(className);
    }
    
    public String[] getProductsByFilterList(ArrayList<String> filters) {
    	System.out.println("Products: "); 
    	ArrayList<String> filteredProducts=getAllProducts();
    	for(String className : filters) {
    	ArrayList<String> products=getProductsOfClass(className);
    	filteredProducts.retainAll(products);
    	System.out.println("Products: "+products); 
    	}
    	return filteredProducts.toArray(new String[0]);
    }

	public ArrayList <String> getProductDataValues(String productName) {
		return ontologyController.getProductDataValues(productName);
		// TODO Auto-generated method stub
	}
	public ArrayList <String> getComposition(String productName) {
		return ontologyController.getComposition(productName);
	}
    

}
