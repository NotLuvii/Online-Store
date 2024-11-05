package unb.cb.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {
    @Autowired
    private Customer customer;
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("there");
        //customer.addCustomer("JimmyBillBob", "superstrongpassword");
    }
}
