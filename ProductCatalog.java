package com.rgt.onlineshoping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class ProductCatalog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5596394304014330832L;
	private HashMap<String, Product> products;

    public ProductCatalog() {
        products = new HashMap<>();
    }
/**
 * load the products from text file
 * @param fileName
 */
    @SuppressWarnings("unchecked")
	public void loadProducts(String fileName) {
        try {
        	ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            products =  (HashMap<String, Product>) objectInputStream.readObject();
            System.out.println("Product catalog loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading product catalog: " + e.getMessage());
        }
    }
/**
 * create the products text file 
 * @param fileName
 */
    public void saveProducts(String fileName) {
        try {
        	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(products);
            System.out.println("Product catalog saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving product catalog: " + e.getMessage());
        }
    }
/**
 * 
 * @param name
 * @return
 */
    public Product getProduct(String name) {
        return products.get(name);
    }
/**
 * add a product to file
 * @param product
 */
    public void addProduct(Product product) {
    	if(products.containsValue(product.getName())) {
    		System.out.println("Product name is already existed.");
    	}
    	else{
    		products.put(product.getName(), product);
    	}
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public void displayProducts() {
    	if(products.isEmpty()) {
    		System.out.println("Product catlog is Empty");
    	}else {
    		 for (Product product : products.values()) {
    	            System.out.println(product);    
    	        }
    		 System.out.println("Products are loaded sucessfully");
    	}
       
    }
}
