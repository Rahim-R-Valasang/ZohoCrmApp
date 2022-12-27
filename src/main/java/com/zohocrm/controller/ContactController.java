package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zohocrm.entities.Billing;
import com.zohocrm.entities.Contact;
import com.zohocrm.services.BillingServices;
import com.zohocrm.services.ContactService;

@Controller
public class ContactController {

	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private BillingServices billingServices;
	
	
	@RequestMapping("/listContacts")
	public String contactLead(Model model) {
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		return "list_contacts";
	}
	
	@RequestMapping("/createBill")
	public String createBill(@RequestParam("id") long id, Model model) {
		Contact contact = contactService.getContactById(id);
		model.addAttribute("contact", contact);
		return "generate_bill";
	}
	
	@RequestMapping("/saveBill")
	public String saveBill(Billing bill, Model model) {
		billingServices.generateBill(bill);
		List<Billing> bills = billingServices.getAllBills();
		model.addAttribute("bills", bills);
		return "list_bills";
	}
	
}
