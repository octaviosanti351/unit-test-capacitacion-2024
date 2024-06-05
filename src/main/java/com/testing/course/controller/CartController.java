package com.testing.course.controller;

import com.testing.course.business.Cart;
import com.testing.course.business.PaymentService;
import com.testing.course.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/cart")
public class CartController {
    private final Cart cartSession;
    private final PaymentService paymentService;

    public CartController(Cart cartSession, PaymentService paymentService) {
        this.cartSession = cartSession;
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity addToCart( @RequestParam(value = "isbn") final String isbn, @RequestParam(value = "quantity") final int quantity){
        Map<String, Object> response = new HashMap<>();

        try {
            cartSession.add(isbn, quantity);
            response.put("products", cartSession.getProducts());
            response.put("total", cartSession.total());
            return ResponseEntity.ok(response);
        }
        catch (Exception ex){
            ex.printStackTrace();
            response.put("error", ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
        paymentService.savePayment(payment);
        return ResponseEntity.ok(payment);
    }
}
