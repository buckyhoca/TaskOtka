package com.example.Task2.Repository;
import com.example.Task2.Model.Cards;
import com.example.Task2.Model.CardsTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// это репозиторий транзакций автомобилейthis is the Cars Transactions Repository
@Repository
public interface CardsTransactionsRepository extends JpaRepository<CardsTransactions, Long> {
    List<CardsTransactions> findByCard(Cards cards);


}
