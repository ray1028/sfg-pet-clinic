package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	
	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
		System.out.println("inside di and ownservice has " + ownerService.findAll().size());
	}

	@RequestMapping({"","/","/index","/index.html"})
	public String listOwners(Model model) {
		
	System.out.println("Total record " + 
			ownerService.findAll().size());
	
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}
}
