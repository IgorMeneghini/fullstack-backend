package com.example.myapp.service;

import com.example.myapp.model.Card;
import com.example.myapp.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(int id) {
        return cardRepository.findById(id);
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCardById(int id) {
        cardRepository.deleteById(id);
    }

    public List<Card> getCardsByStatus(String status) {
        return cardRepository.findByStatus(status);
    }
}