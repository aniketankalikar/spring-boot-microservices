package com.advaya.userservice.repositories;

import com.advaya.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Token findByValueAndIsDeleted(String value, Boolean isDeleted);
    Token findByValueAndIsDeletedAndExpiryAtGreaterThan(String value, Boolean isDeleted, LocalDate date);
}
