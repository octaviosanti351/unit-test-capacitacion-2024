package com.testing.course;

import com.testing.course.business.PaymentService;
import com.testing.course.model.CreditCard;
import com.testing.course.model.Payment;
import com.testing.course.repository.CreditCardRepository;
import com.testing.course.repository.PaymentRepository;
import com.testing.course.repository.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentTestSpy {

    @Spy
    private PaymentRepository paymentRepository;

    @Spy
    private CreditCardRepository creditCardRepository;

    @Spy
    private UserList userList;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;
    private CreditCard creditCard;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setTotal(100D);
        payment.setCreditCardNumber("1234567890123456");
        payment.setUsername("user1");

        creditCard = new CreditCard();
        creditCard.setNumber("1234567890123456");
        creditCard.setValid(true);
    }

    @Test
    public void testSavePayment_Success() {
        when(creditCardRepository.findById(anyString())).thenReturn(Optional.of(creditCard));
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.savePayment(payment);

        verify(paymentRepository).save(payment);
        assertEquals(payment, result);
    }


    @Test
    public void testSavePayment_UserNotValidated() {
        payment.setUsername("user2");

        assertThrows(IllegalArgumentException.class, () -> paymentService.savePayment(payment));
    }

}

