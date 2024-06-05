package com.testing.integration;
import com.testing.course.CourseApplication;
import com.testing.course.business.Cart;
import com.testing.course.config.CartConfig;
import com.testing.course.config.DatabaseConfig;
import com.testing.course.model.CreditCard;
import com.testing.course.repository.BookRepository;
import com.testing.course.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;


import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CourseApplication.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CartControllerIntegrationTests {

    private MockMvc mockMvc;

    //@Mock
    //@MockBean
    CreditCardRepository creditCardRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testAddToCartSuccess() throws Exception {


        mockMvc.perform(post("/v1/cart/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("isbn", "978-3-16-148410-0")
                        .param("quantity", "1"))
                .andExpect(status().isOk());

    }

    @Test
        public void testAddToCartFailure() throws Exception {

        mockMvc.perform(post("/v1/cart/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("isbn", "978-3-16-1484123-0")
                        .param("quantity", "1"))
                .andExpect(status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Product is not sell by supermarket"));

    }

    /*
    @Transactional
    @Test
    public void testSavePayment() throws Exception {

        // Puede cargarse desde un archivo tambien
        String jsonPayload = "{ \"id\": \"666\", \"total\": 50, \"creditCardNumber\": \"1234-5678-9012-3410\", \"username\": \"user1\" }";

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("1234-5678-9012-3410");
        creditCard.setValid(true);


        Mockito.when(creditCardRepository.findByNumber(ArgumentMatchers.anyString())).thenReturn(Optional.of(creditCard));
        mockMvc.perform(post("/v1/cart/save") // Replace "/your-endpoint-path" with the actual endpoint path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk()) // Expecting a 200 OK response
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("666")) // Optionally, assert the response contains the expected values
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNumber").value("1234-5678-9012-3410"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user1"));
    }
    */


    @Transactional
    @Test
    public void testSavePaymentWithoutMock() throws Exception {

        // Puede cargarse desde un archivo tambien
        String jsonPayload = "{ \"id\": \"666\", \"total\": 50, \"creditCardNumber\": \"1234-5678-9012-3456\", \"username\": \"user1\" }";

        mockMvc.perform(post("/v1/cart/save") // Replace "/your-endpoint-path" with the actual endpoint path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk()) // Expecting a 200 OK response
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("666")) // Optionally, assert the response contains the expected values
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNumber").value("1234-5678-9012-3456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user1"));
    }


}
