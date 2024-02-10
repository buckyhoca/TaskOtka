package com.example.Task2.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "cards_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

// Это моя модель Cards_transactions - > This is my Cards_transactions entity
public class CardsTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Cards card;

    private String type;

    private Long amount;

    private Long oldBalance;

    private Long newBalance;

    private Date dt;
}
