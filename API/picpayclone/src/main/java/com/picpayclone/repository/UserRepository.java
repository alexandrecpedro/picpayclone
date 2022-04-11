package com.picpayclone.repository;

import com.picpayclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// id type = Long
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Modifying
    @Query("Update User u set u.balance = u.balance - ?2 where u.login = ?1")
    void updateDecrementBalance(String login, Double value);

    @Modifying
    @Query("Update User u set u.balance = u.balance + ?2 where u.login = ?1")
    void updateIncreaseBalance(String login, Double value);
}
