package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface ICRUDProductService {
	//CRUD
	
	//C - create
	
	//public abstract - visas interfaca esosas funkc ir uzreiz public abstract
	void addNewProduct(String title, String description, float price, int quantity) throws Exception;
	
	//R - read/retrieve
	ArrayList<Product> retrieveAllProducts();
		
	// retrieve by id
	Product retrieveProductById(long id) throws Exception;
	//U - update
	void updateProductById(long id, String title, String description, float price, int quantity) throws Exception;
	//D - delete
	void deleteProductById(long id) throws Exception;
}
