package com.example.myapp;

import com.example.myapp.model.Card;
import com.example.myapp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class MyappApplication implements CommandLineRunner {

    @Autowired
    private CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Card card = new Card();
        card.setContactFirstName("Vidal Meneghini");
        card.setDateCreated(new Timestamp(System.currentTimeMillis()));
        card.setSuburb("New York");
        card.setCategory(1);
        card.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Curabitur a turpis metus. In hac habitasse platea dictumst. Sed ac est enim. " +
                "Suspendisse potenti. Nullam sit amet nisi nunc.");
        card.setNewField("Lead Invitation");
        card.setPrice(499.00f);
        card.setStatus("Invited");
        card.setPhoneNumber("1234567890");
        card.setEmail("test@test.com");
        cardService.saveCard(card);

        card.setContactFirstName("Ester Meneghini");
        card.setDateCreated(new Timestamp(System.currentTimeMillis()));
        card.setSuburb("New York");
        card.setCategory(1);
        card.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Curabitur a turpis metus. In hac habitasse platea dictumst. Sed ac est enim. " +
                "Suspendisse potenti. Nullam sit amet nisi nunc.");
        card.setNewField("Lead Invitation");
        card.setPrice(1300.00f);
        card.setStatus("Invited");
        card.setPhoneNumber("1234567890");
        card.setEmail("test@test.com");
        cardService.saveCard(card);
    }
}
