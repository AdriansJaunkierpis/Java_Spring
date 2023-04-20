package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

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
}
