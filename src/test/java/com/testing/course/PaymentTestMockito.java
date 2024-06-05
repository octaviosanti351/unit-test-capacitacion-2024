package com.testing.course;

import com.testing.course.business.PaymentService;
import com.testing.course.model.CreditCard;
import com.testing.course.model.Payment;
import com.testing.course.model.User;
import com.testing.course.repository.CreditCardRepository;
import com.testing.course.repository.PaymentRepository;
import com.testing.course.repository.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentTestMockito {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CreditCardRepository creditCardRepository;


    @Mock
    UserList userList;

    @InjectMocks
    PaymentService paymentService;

    private Payment payment;
    private CreditCard creditCard;
    private ArrayList<User> users;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setTotal(100D);
        payment.setCreditCardNumber("1234567890123456");
        payment.setUsername("user1");

        users = new ArrayList<>();

        users.add(new User(true, "user1"));
        users.add(new User(false, "user2"));
        users.add(new User(true, "user3"));

        creditCard = new CreditCard();
        creditCard.setNumber("1234567890123456");
        creditCard.setValid(true);
    }
    @Test
    public void testLegalPaymentWithMock(){

        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        when(creditCardRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(creditCard));
        when(userList.userList()).thenReturn(users);

        Payment savedPayment = paymentService.savePayment(payment);

        verify(paymentRepository, times(2)).save(payment);
        assertEquals(payment, savedPayment);
    }


}
