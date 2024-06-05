package com.testing.course.repository;

import com.testing.course.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreditCardRepository extends CrudRepository<CreditCard, String> {

    Optional<CreditCard> findByNumber(String number);
}
