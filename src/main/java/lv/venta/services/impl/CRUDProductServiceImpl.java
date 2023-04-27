package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService {

	
	private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(
			new Product("Pear", "U124", 1.2f, 9),
			new Product("Tree", "Apple", 200.2f, 3),
			new Product("Car", "Cool", 5000.99f, 1)
			));
	
	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {
		//TODO verify with regex title and description
		if (title != null && description != null && price > 0 && price < 10000 && quantity > 0 && quantity < 100000) {
			boolean isFound = false;
			for (Product temp: allProducts) {
				if (temp.getTitle().equals(title) && temp.getDescription().equals(description) && temp.getPrice() == price) {
					temp.setQuantity(temp.getQuantity() + quantity);
					isFound = true;
					break;
				}
				if (!isFound) {
					Product newProduct = new Product(title, description, price, quantity);
					allProducts.add(newProduct);
				}
			}
		} else {
			throw new Exception("Incorrect params");
		}
		
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return allProducts;
	}

	@Override
	public Product retrieveProductById(long id) throws Exception {
		if (id > 0) {
			for (Product temp: allProducts) {
				if (temp.getId() == id) {
					return temp;
				}
			}
			throw new Exception("ID does not correspond to product");
		} else {
			throw new Exception("ID must be positive");
		}
	}

	@Override
	public void updateProductById(long id, String title, String description, float price, int quantity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductById(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
