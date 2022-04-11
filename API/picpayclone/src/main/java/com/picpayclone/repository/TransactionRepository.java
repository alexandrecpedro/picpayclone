package com.picpayclone.repository;

import com.picpayclone.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByOrigin_LoginOrDestination_Login(String loginOrigin, String loginDestination,
                                                            Pageable pagination);
}
