package com.example.Task2.Repository;
import com.example.Task2.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Это мой репозиторий пользователей - This is my User repository
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
