package com.testing.course.business;

import com.testing.course.model.CreditCard;
import com.testing.course.model.Payment;
import com.testing.course.model.User;
import com.testing.course.repository.CreditCardRepository;
import com.testing.course.repository.PaymentRepository;
import com.testing.course.repository.UserList;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {


    PaymentRepository paymentRepository;
    CreditCardRepository creditCardRepository;
    UserList userList;

    public PaymentService(PaymentRepository paymentRepository, CreditCardRepository creditCardRepository, UserList userList) {
        this.paymentRepository = paymentRepository;
        this.creditCardRepository = creditCardRepository;
        this.userList = userList;
    }

    public Payment savePayment(Payment payment) {
        Optional<User> userOpt = userList.userList().stream()
                .filter(userFind -> userFind.getUsername().equals(payment.getUser()))
                .findFirst();

        if(userOpt.isEmpty() || !userOpt.get().isValid()){
            throw new IllegalArgumentException("User must be validated.");
        }

        if (payment.getTotal() <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than zero.");
        }

        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(payment.getCreditCardNumber());
        CreditCard creditCard;
        if(creditCardOptional.isPresent()){
            creditCard = creditCardOptional.get();
        }
        else{
            throw new IllegalArgumentException("Credit card not exist");
        }

        if(creditCard.isValid()){
            return paymentRepository.save(payment);
        }
        else{
            throw new IllegalArgumentException("Credit card is invalid");
        }

    }



}
