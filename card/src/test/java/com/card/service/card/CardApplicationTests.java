package com.card.service.card;


import com.card.service.card.dto.CardDTO;
import com.card.service.card.entities.Card;
import com.card.service.card.repositories.CardRepository;
import com.card.service.card.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
class CardApplicationTests {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCard() {
        Card card = new Card();
        when(cardRepository.save(card)).thenReturn(card);

        Card savedCard = cardService.addCard(card);

        assertEquals(card, savedCard);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void testGetCard() {
        Card card = new Card();
        when(cardRepository.findById(1)).thenReturn(Optional.of(card));

        Optional<Card> foundCard = cardService.getCard(1);

        assertTrue(foundCard.isPresent());
        assertEquals(card, foundCard.get());
        verify(cardRepository, times(1)).findById(1);
    }

    @Test
    void testFindCard() {
        Card card = new Card();
        when(cardRepository.findByName("test")).thenReturn(Arrays.asList(card));

        List<Card> cards = cardService.findCard("test");

        assertEquals(1, cards.size());
        assertEquals(card, cards.get(0));
        verify(cardRepository, times(1)).findByName("test");
    }

    @Test
    void testFindAll() {
        Card card = new Card();
        when(cardRepository.findAll()).thenReturn(Arrays.asList(card));

        List<CardDTO> cardDTOs = cardService.findAll();

        assertEquals(1, cardDTOs.size());
        // You might want to add more assertions based on CardDTO properties
        verify(cardRepository, times(1)).findAll();
    }

}
