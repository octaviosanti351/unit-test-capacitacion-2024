package com.testing.course.controller;

import com.testing.course.business.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/cart")
public class CartController {
    private final Cart cartSession;

    public CartController(Cart cartSession) {
        this.cartSession = cartSession;
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
}
