package com.example.cards.service;

import com.example.cards.dtos.CardsDTO;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDTO fetchCard(String mobileNumber);


    boolean updateCard(CardsDTO cardsDto);


    boolean deleteCard(String mobileNumber);
}
