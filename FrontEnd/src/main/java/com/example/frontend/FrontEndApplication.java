package com.example.frontend;

import com.example.frontend.SharedDataTypes.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
public class FrontEndApplication {
	static User loggedIn;

	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
		Scanner scan = new Scanner(System.in);
		Login login = new Login();
		login.login();
	}
}
