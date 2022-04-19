package com.springdemo.controller;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {


    //need to inject a customer dao
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){

        //get the customer from the dao
        List<Customer> theCustomers = customerService.getCutomers();

        //add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "listCustomer";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        theModel.addAttribute("customer", new Customer());

        return "customerFrom";
    }


    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){

        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theCustomerId,Model theModel){

        Customer theCustomer = customerService.getCustomer(theCustomerId);

        theModel.addAttribute("customer",theCustomer);

        return "customerFrom";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId){
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

}
