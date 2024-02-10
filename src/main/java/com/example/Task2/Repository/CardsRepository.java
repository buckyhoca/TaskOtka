package com.example.Task2.Repository;
import com.example.Task2.Model.Cards;
import com.example.Task2.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByUser(Users user);
}
