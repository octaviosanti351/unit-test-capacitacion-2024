package com.testing.course.repository;

import com.testing.course.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, String> {
}
