package com.api.service;

import com.api.model.BaralhoEmbaralhadoDeckCartaModel;
import com.api.model.BaralhoEmbaralhadoDeckModel;
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

    @Value("${baralho.embaralhado.deck.carta}")
    private String baralho_embaralhado_deck_carta;

    public BaralhoEmbaralhadoDeckModel apiBaralhoEmbaralhadoDeck() {
        RestTemplate template = new RestTemplate();
        BaralhoEmbaralhadoDeckModel baralhoEmbaralhadoDeckModel = template.getForObject(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckModel.class);
        return baralhoEmbaralhadoDeckModel;
    }

    public BaralhoEmbaralhadoDeckCartaModel apiBaralhoEmbaralhadoDeckCarta(String deckId) {
        RestTemplate template = new RestTemplate();
        String urlDeckId = baralho_embaralhado_deck_carta.concat(deckId);
        String url = urlDeckId.concat("/draw/?count=5");
        BaralhoEmbaralhadoDeckCartaModel baralhoEmbaralhadoDeckCartaModel = template.getForObject(url, BaralhoEmbaralhadoDeckCartaModel.class);
        return baralhoEmbaralhadoDeckCartaModel;
    }
}
