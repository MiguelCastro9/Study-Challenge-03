package com.api;

import com.api.model.BaralhoEmbaralhadoDeckCartaModel;
import com.api.model.BaralhoEmbaralhadoDeckModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Value;

@SpringBootTest
class ApiApplicationTests {

    @Value("${baralho.embaralhado.deck}")
    private String baralho_embaralhado_deck;

    @Value("${baralho.embaralhado.deck.carta}")
    private String baralho_embaralhado_deck_carta;

    @Value("${total.carta}")
    private Integer total_carta;

    @Test
    void testApiBaralhoEmbaralhadoDeckModel() {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaralhoEmbaralhadoDeckModel> responseEntity = restTemplate.getForEntity(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckModel.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        BaralhoEmbaralhadoDeckModel baralhoEmbaralhadoDeckModel = responseEntity.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals("true", baralhoEmbaralhadoDeckModel.getSuccess());
        assertEquals("52", baralhoEmbaralhadoDeckModel.getRemaining());
        assertEquals("true", baralhoEmbaralhadoDeckModel.getShuffled());
    }

    @Test
    void testApiBaralhoEmbaralhadoDeckCartaModel() {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaralhoEmbaralhadoDeckModel> responseEntity1 = restTemplate.getForEntity(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckModel.class);
        BaralhoEmbaralhadoDeckModel baralhoEmbaralhadoDeckModel = responseEntity1.getBody();

        String url = baralho_embaralhado_deck_carta.concat(baralhoEmbaralhadoDeckModel.getDeck_id()) + "/draw/?count=" + total_carta;
        ResponseEntity<BaralhoEmbaralhadoDeckCartaModel> responseEntity2 = restTemplate.getForEntity(url, BaralhoEmbaralhadoDeckCartaModel.class);
        HttpStatus statusCode = responseEntity2.getStatusCode();
        BaralhoEmbaralhadoDeckCartaModel baralhoEmbaralhadoDeckCartaModel = responseEntity2.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals("true", baralhoEmbaralhadoDeckCartaModel.getSuccess());
    }
}
