package lv.venta.controller;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.services.ICRUDProductService;

@Controller
public class FirstController {

	@Autowired
	private ICRUDProductService CRUDservice;

	@GetMapping("/hello") // localhost:8080/hello
	public String getHelloFunc() {
		System.out.println("Sveiki!");
		return "hello-page";// there will be hello-page.html
	}

	// TODO create controller with model and add String in the model
	@GetMapping("/msg") // localhost:8080/msg
	public String getMsgFunc(Model model) {
		model.addAttribute("packet", "Message from Karina");
		return "msg-page";// will show msg-page.html
	}

	// TODO controller function which will send new product to frontend
	@GetMapping("/one-product") // localhost:8080/one-product
	public String getOneProductFunc(Model model) {
		Product prod = new Product("Apple", "Tasty", 1.2f, 9);
		model.addAttribute("packet", prod);
		return "one-product-page";// will show one-product-page.html
	}

	@GetMapping("/all-products") // localhost:8080/all-products
	public String getAllproductsFunc(Model model) {
		model.addAttribute("packet", CRUDservice.retrieveAllProducts());
		return "all-products-page";// will show all-products-page.html
	}
	// TODO create a html page which will show all products

	// TODO controller for localhost:8080/all-products-find?id=2

	@GetMapping("/all-products-find") // localhost:8080/all-products-find?id=2
	public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("packet", prod);
			return "one-product-page";// will call one-product-page.html
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";// will call error-page.html
		}

	}

	// TODO controller for localhost:8080/all-products/2
	@GetMapping("/all-products/{id}") // localhost:8080/all-products/2
	public String getAllProductsByIdFunc(@PathVariable("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("packet", prod);
			return "one-product-page";// will call one-product-page.html
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";// will call error-page.html
		}
	}

	@GetMapping("/add-product") // localhost:8080/add-product
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());// send and empty product
		return "add-product-page";// will call add-product-page.html
	}

	@PostMapping("/add-product")
	public String postAddProductFunc(@Valid Product product, BindingResult result) { // retrieve product with all
																						// parameters

		//TODO add validation also in update
				//TODO add Your own massages in validation annotations
				
				if (!result.hasErrors()) {
					try {
						CRUDservice.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(),
								product.getQuantity());
						return "redirect:/all-products";// will call /all-products endpoint
					} catch (Exception e) {
						return "redirect:/error";
					}
				}
				else
				{
					return "add-product-page";
				}
			}

			@GetMapping("/update-product/{id}") // localhost:8080/update-product/2
			public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {

				try {
					Product prod = CRUDservice.retrieveProductById(id);
					model.addAttribute("product", prod);
					return "update-product-page";// will call update-product-page.html
				} catch (Exception e) {
					model.addAttribute("packetError", e.getMessage());
					return "error-page";// will call error-page.html
				}

			}

			@PostMapping("/update-product/{id}")
			public String postUpdateProductFunc(@PathVariable("id") long id, @Valid Product product,
					BindingResult result)// edited product
			{
				
				if(!result.hasErrors()) {
					try {
						CRUDservice.updateProductById(id, product.getTitle(), product.getDescription(), product.getPrice(),
								product.getQuantity());
						return "redirect:/all-products/" + id; // will call localhost:8080/all-products/2 endpoint
					} catch (Exception e) {
						return "redirect:/error"; // will call localhost:8080/error
					}
				}
				else
				{
					return "update-product-page";
				}

			}

	@GetMapping("/error") // localhost:8080/error
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wrong id");
		return "error-page";// will call error-page.html
	}

	// TODO delete
	@GetMapping("/delete-product/{id}") // localhost:8080/delete-product/2
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
		try {
			CRUDservice.deleteProductById(id);
			model.addAttribute("packet", CRUDservice.retrieveAllProducts());
			return "all-products-page";// will call all-products-page.html
		} catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";// will call error-page.html
		}

	}

}