package com.card.service.card.mappers;

import com.card.service.card.dto.CardDTO;
import com.card.service.card.entities.Card;

public interface CardEntityToCardDTO {
    CardDTO cardEntityToCardDTO(Card card);
}
