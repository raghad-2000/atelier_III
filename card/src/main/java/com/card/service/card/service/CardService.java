package com.card.service.card.service;

import com.card.service.card.dto.CardDTO;
import com.card.service.card.entities.Card;
import com.card.service.card.mappers.impl.CardEntityToCardDTOImpl;
import com.card.service.card.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    private final CardEntityToCardDTOImpl cardEntityToCardDTO = new CardEntityToCardDTOImpl();

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }
    public Optional<Card> getCard(long id) {
        return cardRepository.findById(id);
    }
    public List<Card> findCard(String name) {
        return cardRepository.findByName(name);
    }
    public List<CardDTO> findAll() {
        List<Card> cardList = cardRepository.findAll();
        List <CardDTO> cardDTOList = new ArrayList<>();
        for(Card card: cardList) {
            CardDTO cardDTO = cardEntityToCardDTO.cardEntityToCardDTO(card);
            cardDTOList.add(cardDTO);
        }
        return cardDTOList;
    }

    public List<Long> getRandomCards(int qty) {
        List<Card> cardList = cardRepository.findAll();
        List<Long> randomCards = new ArrayList<>();
        Random randomNumbers = new Random();
        for (int i = 0; i < qty;  i++)
        {
            randomCards.add(randomNumbers.nextLong(cardList.size()) + 1);
        }
        return randomCards;
    }

    private String nameFormatter(String name) {
        return name.replace(' ','_');
    }
}