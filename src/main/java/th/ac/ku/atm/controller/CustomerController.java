package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.service.CustomerService;
import th.ac.ku.atm.model.Customer;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    ArrayList<Customer> customers = new ArrayList<>();
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public String getCustomerPage(Model model) {

        model.addAttribute("allCustomers", customerService.getCustomers());
        return "customer";
    }
    @PostMapping
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:customer";
    }


}
