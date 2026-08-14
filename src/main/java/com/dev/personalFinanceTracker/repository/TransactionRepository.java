package com.dev.personalFinanceTracker.repository;

import com.dev.personalFinanceTracker.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllTransactionsByAccountId(Long accountId, Pageable pageable);

    @Query(value = "SELECT t.account FROM transactions t " +
            "WHERE t.transaction_id = :id", nativeQuery = true)
    Optional<Long> findAccountIdByTransactionId(@Param(value = "id") long id);

    @Query(value = "SELECT * FROM transactions t " +
            "WHERE t.timestamp >= :startDate " +
            "AND t.timestamp <= :endDate", nativeQuery = true)
    Page<Transaction> getAllTransactionsByMonthAndYear(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
