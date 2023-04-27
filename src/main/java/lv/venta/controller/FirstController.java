package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Controller
public class FirstController {

	@Autowired
	private ICRUDProductService CRUDService;
	
	
	
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
		model.addAttribute("packet", CRUDService.retrieveAllProducts());
		return "all-products";
	}
	
	@GetMapping("/all-products-find")
	public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) { //all-products-find?id=2
		
		try {
			Product prod = CRUDService.retrieveProductById(id);
			model.addAttribute(prod);
			return "one-product-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/all-products/{id}")
	public String getAllProductsByIdFunc(@PathVariable("id") long id, Model model) { //all-products-find?/2
		try {
			Product prod = CRUDService.retrieveProductById(id);
			model.addAttribute(prod);
			return "one-product-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping ("/add-product")
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping("/add-product")
	public String postAddProductFunc(Product product) {
		//TODO verify if this product already exists

		try {
			CRUDService.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/all-products";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}
	
	@GetMapping("/update-product/{id}")
	public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
		try {
			Product prod = CRUDService.retrieveProductById(id);
			model.addAttribute("product" ,prod);
			return "update-products-page"; //will call localhost:8080/all-products/2 endpoint
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "redirect:/error"; // will call localhost:8080/error
		}
	}
	@PostMapping("/update-product/{id}") 
	public String postUpdateProductFunc(@PathVariable("id") long id, Product product) {
		try {
			CRUDService.updateProductById(id, product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/all-products/"+id; //will call localhost:8080/all-products/2 endpoint
		} catch (Exception e) {
			return "redirect:/error"; // will call localhost:8080/error
		}
	}
	@GetMapping("/error")
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wrong id");
		return "/error";
	}
	
	@GetMapping("/delete-product/{id}")
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
		try {
			CRUDService.deleteProductById(id);
			model.addAttribute("packet", CRUDService.retrieveAllProducts());
			return "all-products-page";
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}
}
