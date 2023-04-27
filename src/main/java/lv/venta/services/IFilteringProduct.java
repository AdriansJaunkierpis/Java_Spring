package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilteringProduct {

	ArrayList<Product> filterByPriceLessThan(float priceThreshold) throws Exception;
	
	ArrayList<Product> filterByQuantity(int quantity) throws Exception;
	
	//TODO ascending or descending
	ArrayList<Product> sort();
}
