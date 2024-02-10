package com.example.Task2.Repository;
import com.example.Task2.Model.Cards;
import com.example.Task2.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
// это репозиторий карточек -this is the Cards Repository
@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByUser(Users user);
}
