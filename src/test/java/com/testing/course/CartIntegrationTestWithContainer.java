package com.testing.course;

import com.testing.course.config.DatabaseConfig;
import com.testing.course.model.CreditCard;
import com.testing.course.repository.CreditCardRepository;
import com.testing.course.repository.PaymentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(classes = {TestDatabaseConfig.class, CourseApplication.class})
public class CartIntegrationTestWithContainer {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    CreditCardRepository creditCardRepository;


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testSavePaymentWithoutMock() throws Exception {

        // Puede cargarse desde un archivo tambien
        String jsonPayload = "{ \"id\": \"166\", \"total\": 50, \"creditCardNumber\": \"3456-7890-1234-5630\", \"username\": \"user1\" }";


        mockMvc.perform(post("/v1/cart/save") // Replace "/your-endpoint-path" with the actual endpoint path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk()) // Expecting a 200 OK response
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("166")) // Optionally, assert the response contains the expected values
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNumber").value("3456-7890-1234-5630"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user1"));
    }


}
