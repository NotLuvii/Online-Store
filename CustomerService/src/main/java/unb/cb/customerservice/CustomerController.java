package unb.cb.customerservice;

import org.springframework.web.bind.annotation.*;
import unb.cb.customerservice.SharedDataTypes.Product;
import unb.cb.customerservice.SharedDataTypes.User;

@RestController
public class CustomerController {
    CustomerService customer = new CustomerService();
    @PostMapping("/addCustomer")
    public void addCustomer(@RequestParam("username") String username, @RequestParam("password") String password){

        customer.addCustomer(username, password);
    }

    @GetMapping("/getUser")
    public void getCustomer(){
        customer.getCustomer();
    }

    @PostMapping("receiveCustomer")
    public void receiveCustomer(@RequestBody User user){
        customer.sendCustomer(user);
    }
}
