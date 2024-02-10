package com.example.Task2.Service.Impl;
import com.example.Task2.Model.*;
import com.example.Task2.Repository.CardsRepository;
import com.example.Task2.Repository.CardsTransactionsRepository;
import com.example.Task2.Repository.UsersRepository;
import com.example.Task2.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


//это класс реализации InformationService
@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsTransactionsRepository cardsTransactionRepository;

//    класс реализации отделен от интерфейса InformationService
    @Override
    public List<Map<String, Object>> getUserInfo() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Users> users = usersRepository.findAll();


//        этот метод получает всю информацию о пользователях из трех таблиц и возвращается в List
        for (Users user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("dob", user.getDob());
            userMap.put("dt", user.getDt());
            userMap.put("email", user.getEmail());
            userMap.put("fio", user.getFio());
            userMap.put("phone", user.getPhone());
            userMap.put("status", user.getStatus());
            List<Map<String, Object>> cardsList = new ArrayList<>();
            List<Cards> cards = cardsRepository.findByUser(user);

            for (Cards card : cards) {
                Map<String, Object> cardMap = new HashMap<>();
                cardMap.put("id", card.getId());
                cardMap.put("balance", card.getBalance());
                cardMap.put("card_expire", card.getCardExpire());
                cardMap.put("card_name", card.getCardName());
                cardMap.put("card_number", card.getCardNumber());
                cardMap.put("dt", card.getDt());
                cardMap.put("status", card.getStatus());

                List<Map<String, Object>> transactionsList = new ArrayList<>();

                List<CardsTransactions> transactions = cardsTransactionRepository.findByCard(card);
                for (CardsTransactions transaction : transactions) {
                    Map<String, Object> transactionMap = new HashMap<>();
                    transactionMap.put("id", transaction.getId());
                    transactionMap.put("amount", transaction.getAmount());
                    transactionMap.put("dt", transaction.getDt());
                    transactionMap.put("new_balance", transaction.getNewBalance());
                    transactionMap.put("old_balance", transaction.getOldBalance());
                    transactionMap.put("type", transaction.getType());
                    transactionsList.add(transactionMap);
                }
                cardMap.put("transactions", transactionsList);
                cardsList.add(cardMap);
            }
            userMap.put("cards", cardsList);
            result.add(userMap);
        }
        return result;
    }
}
