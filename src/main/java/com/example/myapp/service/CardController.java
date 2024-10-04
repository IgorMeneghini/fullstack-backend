package com.example.myapp.service;

import com.example.myapp.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards(@RequestParam(required = false) String status) {
        if (status != null) {
            return cardService.getCardsByStatus(status);
        }
        return cardService.getAllCards();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCardStatus(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Optional<Card> cardOptional = cardService.getCardById(id);

        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            float oldPrice = card.getPrice();
            EmailService.logUpdates(id, updates, oldPrice); 

            if (updates.containsKey("status")) {
                String newStatus = (String) updates.get("status");
                card.setStatus(newStatus);
            }

            if (updates.containsKey("price")) {
                float newPrice = ((Number) updates.get("price")).floatValue();
                if (newPrice != oldPrice) {
                    card.setPrice(newPrice);
                }
            }

            Card updatedCard = cardService.updateCard(card);
            return ResponseEntity.ok(updatedCard);
        }

        return ResponseEntity.notFound().build();
    }
}