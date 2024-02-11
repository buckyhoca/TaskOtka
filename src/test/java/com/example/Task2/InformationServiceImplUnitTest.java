package com.example.Task2;
import com.example.Task2.Model.Cards;
import com.example.Task2.Model.CardsTransactions;
import com.example.Task2.Model.Users;
import com.example.Task2.Repository.CardsRepository;
import com.example.Task2.Repository.CardsTransactionsRepository;
import com.example.Task2.Repository.UsersRepository;
import com.example.Task2.Service.Impl.InformationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InformationServiceImplUnitTest {
    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CardsRepository cardsRepository;

    @Mock
    private CardsTransactionsRepository cardsTransactionRepository;

    @InjectMocks
    private InformationServiceImpl informationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserInfo() {
        // Здесь мы издеваемся над пользовательскими данными
        List<Users> usersList = new ArrayList<>();
        Users user = new Users();
        user.setId(1L);
        user.setEmail("khamraev782@gmail.com");
        user.setFio("Khamraev Azamat Abduraxmon o'gli");
        usersList.add(user);

        // Здесь мы издеваемся над данными карт
        List<Cards> cardsList = new ArrayList<>();
        Cards card = new Cards();
        card.setId(1L);
        card.setStatus(1);
        card.setUser(user);
        card.setBalance(70000L);
        card.setCardName("Azamat Khamraev");
        card.setCardNumber("999282948182983");
        cardsList.add(card);

        // Здесь мы имитируем данные транзакции
        List<CardsTransactions> transactionsList = new ArrayList<>();
        CardsTransactions transaction = new CardsTransactions();
        transaction.setId(1L);
        transaction.setAmount(2000L);
        transaction.setCard(card);
        transaction.setNewBalance(60000L);
        transaction.setOldBalance(66000L);
        transaction.setType("Debit");
        transaction.setUser(user);
        transactionsList.add(transaction);

        // Здесь мы издеваемся над методами репозитория
        when(usersRepository.findAll()).thenReturn(usersList);
        when(cardsRepository.findByUser(user)).thenReturn(cardsList);
        when(cardsTransactionRepository.findByCard(card)).thenReturn(transactionsList);

        // Вызов тестируемого метода
        List<Map<String, Object>> userInfo = informationService.getUserInfo();

        // Проверка утверждений
        assertEquals(1, userInfo.size());
        Map<String, Object> userMap = userInfo.get(0);
        assertEquals(1L, userMap.get("id"));
        assertEquals("khamraev782@gmail.com", userMap.get("email"));
        assertEquals("Khamraev Azamat Abduraxmon o'gli", userMap.get("fio"));

        List<Map<String, Object>> cards = (List<Map<String, Object>>) userMap.get("cards");
        Map<String, Object> cardMap = cards.get(0);
        assertEquals(1L, cardMap.get("id"));
        assertEquals(1, cardMap.get("status"));
        assertEquals("Azamat Khamraev", cardMap.get("card_name"));
        assertEquals("999282948182983", cardMap.get("card_number"));

        List<Map<String, Object>> transactions = (List<Map<String, Object>>) cardMap.get("transactions");
        Map<String, Object> transactionMap = transactions.get(0);
        assertEquals(1L, transactionMap.get("id"));
        assertEquals(2000L, transactionMap.get("amount"));
        assertEquals("Debit", transactionMap.get("type"));
    }
}
