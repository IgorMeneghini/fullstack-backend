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
        // Test case 1: Decline status
        Card declinedCard = new Card();
        declinedCard.setContactFirstName("Lucas Silva");
        declinedCard.setDateCreated(new Timestamp(System.currentTimeMillis()));
        declinedCard.setSuburb("Los Angeles");
        declinedCard.setCategory(2);
        declinedCard.setDescription("Card status should be Decline.");
        declinedCard.setPrice(450.00f);
        declinedCard.setStatus("Invited");
        declinedCard.setPhoneNumber("9876543210");
        declinedCard.setEmail("lucas@example.com");
        cardService.saveCard(declinedCard);

        // Test case 2: Price >= 500
        Card expensiveCard = new Card();
        expensiveCard.setContactFirstName("Maria Souza");
        expensiveCard.setDateCreated(new Timestamp(System.currentTimeMillis()));
        expensiveCard.setSuburb("San Francisco");
        expensiveCard.setCategory(3);
        expensiveCard.setDescription("Card price is greater than or equal to 500.");
        expensiveCard.setPrice(1200.00f);
        expensiveCard.setStatus("Invited");
        expensiveCard.setPhoneNumber("5432167890");
        expensiveCard.setEmail("maria@example.com");
        cardService.saveCard(expensiveCard);

        // Test case 3: Price < 500
        Card cheapCard = new Card();
        cheapCard.setContactFirstName("Jorge Pereira");
        cheapCard.setDateCreated(new Timestamp(System.currentTimeMillis()));
        cheapCard.setSuburb("Chicago");
        cheapCard.setCategory(4);
        cheapCard.setDescription("Card price is less than 500.");
        cheapCard.setPrice(300.00f);
        cheapCard.setStatus("Invited");
        cheapCard.setPhoneNumber("1112233445");
        cheapCard.setEmail("jorge@example.com");
        cardService.saveCard(cheapCard);
    }
}