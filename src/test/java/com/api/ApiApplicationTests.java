package com.api;

import com.api.response.BaralhoEmbaralhadoDeckCartaResponse;
import com.api.response.BaralhoEmbaralhadoDeckResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class ApiApplicationTests {

    @Value("${baralho.embaralhado.deck}")
    private String baralho_embaralhado_deck;

    @Value("${baralho.embaralhado.deck.carta}")
    private String baralho_embaralhado_deck_carta;

    @Value("${total.carta}")
    private Integer total_carta;

    @BeforeEach
    void setUp() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDatabaseConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:cardsdb", "sa", "");
            boolean isConnected = !connection.isClosed();
            assertEquals(true, isConnected);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testApiBaralhoEmbaralhadoDeckResponse() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaralhoEmbaralhadoDeckResponse> responseEntity = restTemplate.getForEntity(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckResponse.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        BaralhoEmbaralhadoDeckResponse baralhoEmbaralhadoDeckResponse = responseEntity.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals("true", baralhoEmbaralhadoDeckResponse.getSuccess());
        assertEquals("52", baralhoEmbaralhadoDeckResponse.getRemaining());
        assertEquals("true", baralhoEmbaralhadoDeckResponse.getShuffled());
    }

    @Test
    void testApiBaralhoEmbaralhadoDeckCartaResponse() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaralhoEmbaralhadoDeckResponse> responseEntity1 = restTemplate.getForEntity(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckResponse.class);
        BaralhoEmbaralhadoDeckResponse baralhoEmbaralhadoDeckResponse = responseEntity1.getBody();

        String url = baralho_embaralhado_deck_carta.concat(baralhoEmbaralhadoDeckResponse.getDeck_id()) + "/draw/?count=" + total_carta;
        ResponseEntity<BaralhoEmbaralhadoDeckCartaResponse> responseEntity2 = restTemplate.getForEntity(url, BaralhoEmbaralhadoDeckCartaResponse.class);
        HttpStatus statusCode = responseEntity2.getStatusCode();
        BaralhoEmbaralhadoDeckCartaResponse baralhoEmbaralhadoDeckCartaResponse = responseEntity2.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals("true", baralhoEmbaralhadoDeckCartaResponse.getSuccess());
    }
}
