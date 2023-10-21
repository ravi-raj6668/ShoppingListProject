package com.test.neokred.shoppinglistproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Simple Shopping List Application using spring boot, JPA, and Spring Security",
                version = "0.0.1",
                description = "This project is created for NEOKRED assignment purpose. ",
                contact = @Contact(
                        name = "Ravi Raj",
                        email = "ravi74079@gmail.com")
        )
)
public class ShoppingListProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListProjectApplication.class, args);
    }

}
