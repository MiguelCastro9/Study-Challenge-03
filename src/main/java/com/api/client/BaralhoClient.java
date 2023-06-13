package com.api.client;

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
public class BaralhoClient {

    @Value("${baralho.embaralhado.deck}")
    private String baralho_embaralhado_deck;

    @Value("${baralho.embaralhado.deck.carta}")
    private String baralho_embaralhado_deck_carta;
    
    @Value("${total.carta}")
    private Integer total_carta;

    // Criando e embaralhando o baralho.
    public BaralhoEmbaralhadoDeckModel apiBaralhoEmbaralhadoDeck() {
        RestTemplate template = new RestTemplate();
        BaralhoEmbaralhadoDeckModel baralhoEmbaralhadoDeckModel = template.getForObject(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckModel.class);
        return baralhoEmbaralhadoDeckModel;
    }

    // Separando o baralho em decks com cinco cartas.
    public BaralhoEmbaralhadoDeckCartaModel apiBaralhoEmbaralhadoDeckCarta(String deckId) {
        RestTemplate template = new RestTemplate();
        String urlDeckId = baralho_embaralhado_deck_carta.concat(deckId);
        String url = urlDeckId.concat("/draw/?count=" + total_carta);
        BaralhoEmbaralhadoDeckCartaModel baralhoEmbaralhadoDeckCartaModel = template.getForObject(url, BaralhoEmbaralhadoDeckCartaModel.class);
        return baralhoEmbaralhadoDeckCartaModel;
    }
}
