package com.example.Task2.Service.Impl;
import com.example.Task2.Model.*;
import com.example.Task2.Repository.CardsRepository;
import com.example.Task2.Repository.CardsTransactionsRepository;
import com.example.Task2.Repository.UsersRepository;
import com.example.Task2.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsTransactionsRepository cardsTransactionRepository;




    @Override
    public List<Map<String, Object>> getUserInfo() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Users> users = usersRepository.findAll();

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

                List<Cards_transactions> transactions = cardsTransactionRepository.findByCard(card);
                for (Cards_transactions transaction : transactions) {
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
