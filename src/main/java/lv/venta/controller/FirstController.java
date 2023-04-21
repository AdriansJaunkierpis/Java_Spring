package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;

@Controller
public class FirstController {

	private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(
		new Product("Pear", "U124", 1.2f, 9),
		new Product("Tree", "Apple", 200.2f, 3),
		new Product("Car", "Cool", 5000.99f, 1)
		));
	
	
	@GetMapping("/hello") //localhost:8080/hello
	public String getHelloFunc() {
		System.out.println("Sveiki!");
		return "hello-page";//there will be hello-page.html
	}
	
	//TODO create controller with model and add String in the model
	@GetMapping("/msg") //localhost:8080/msg
	public String getMsgFunc(Model model) {
		model.addAttribute("packet", "WorldHello");
		return "msg";
	}
	
	//TODO Product class - title, description, price, quantity
	
	//TODO controller function which will send new product to fronted
	@GetMapping("/one-product")
	public String getOneProductFunc(Model model) {
		Product prod = new Product("Pear", "U124", 1.2f, 9);
		model.addAttribute("packet", prod);
		return "one-product";
	}
	
	@GetMapping("/all-products")
	public String getAllProductsFunc(Model model) {
		model.addAttribute("packet", allProducts);
		return "all-products";
	}
	
	@GetMapping("/all-products-find")
	public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) { //all-products-find?id=2
		if (id > 0) {
			for (Product temp: allProducts) {
				if(temp.getId() == id) {
					model.addAttribute("packet", temp);
					return "one-product";
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page"; //will call error-page , with error would call on any errors
	}
	
	@GetMapping("/all-products/{id}")
	public String getAllProductsByIdFunc(@PathVariable("id") long id, Model model) { //all-products-find?/2
		if (id > 0) {
			for (Product temp: allProducts) {
				if(temp.getId() == id) {
					model.addAttribute("packet", temp);
					return "one-product";
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page"; //will call error-page , with error would call on any errors
	}
	
	@GetMapping ("/add-product")
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping("/add-product")
	public String postAddProductFunc(Product product) {
		//TODO verify if this product already exists
		Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
		allProducts.add(newProduct);
		
		return "redirect:/all-products";
	}
	
	@GetMapping("/update-product/{id}")
	public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
		if (id > 0) {
			for (Product temp: allProducts) {
				if (temp.getId() == id) {
					model.addAttribute("product", temp);
					return "update-product";
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page";
	}
	@PostMapping("/update-product/{id}") 
	public String postUpdateProductFunc(@PathVariable("id") long id, Product product) {
		for (Product temp: allProducts) {
			if (temp.getId() == id) {
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantity(product.getQuantity());
				
				return "redirect:/all-products/"+id; //will call localhost:8080/all-products/2 endpoint
			}
		}
		return "redirect:/error"; // will call localhost:8080/error
	}
	
	@GetMapping("/error")
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wrong id");
		return "/error";
	}
	
	@GetMapping("/delete-product/{id}")
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
		if (id > 0) {
			for (Product temp: allProducts) {
				if (temp.getId() == id) {
					allProducts.remove(temp);
					model.addAttribute("packet", allProducts);
					return "all-products";
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page";
	}
}
