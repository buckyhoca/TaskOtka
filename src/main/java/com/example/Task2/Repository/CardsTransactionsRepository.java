package com.example.Task2.Repository;
import com.example.Task2.Model.Cards;
import com.example.Task2.Model.Cards_transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsTransactionsRepository extends JpaRepository<Cards_transactions, Long> {
    List<Cards_transactions> findByCard(Cards cards);


}
