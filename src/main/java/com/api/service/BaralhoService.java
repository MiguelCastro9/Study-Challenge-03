package com.api.service;

import com.api.model.BaralhoEmbaralhadoDeck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Miguel Castro
 */
@Service
public class BaralhoService {

    @Value("${baralho.embaralhado.deck}")
    private String baralho_embaralhado_deck;

    public BaralhoEmbaralhadoDeck apiBaralhoEmbaralhadoDeck() {
        RestTemplate template = new RestTemplate();
        BaralhoEmbaralhadoDeck baralhoEmbaralhadoDeck = template.getForObject(baralho_embaralhado_deck, BaralhoEmbaralhadoDeck.class);
        return baralhoEmbaralhadoDeck;
    }

}
