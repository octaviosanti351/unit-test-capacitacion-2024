package com.testing.course.config;


import com.testing.course.business.Cart;
import com.testing.course.model.Book;
import com.testing.course.repository.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CartConfig {

    BookRepository booksRepository;

    public CartConfig(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Bean
    public Cart cartSession(){
        List<Book> books = (List<Book>) booksRepository.findAll();
        Map<Object, Double> catalog = new HashMap<>();
        books.forEach(b -> catalog.put(b.getIsbn(), b.getPrice()));
        return new Cart(catalog);
    }
}
