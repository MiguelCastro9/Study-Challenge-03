package com.api.client;

import com.api.response.BaralhoEmbaralhadoDeckCartaResponse;
import com.api.response.BaralhoEmbaralhadoDeckResponse;
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
    public BaralhoEmbaralhadoDeckResponse apiBaralhoEmbaralhadoDeck() {
        RestTemplate template = new RestTemplate();
        BaralhoEmbaralhadoDeckResponse baralhoEmbaralhadoDeckResponse = template.getForObject(baralho_embaralhado_deck, BaralhoEmbaralhadoDeckResponse.class);
        return baralhoEmbaralhadoDeckResponse;
    }

    // Separando o baralho em decks com cinco cartas.
    public BaralhoEmbaralhadoDeckCartaResponse apiBaralhoEmbaralhadoDeckCarta(String deckId) {
        RestTemplate template = new RestTemplate();
        String urlDeckId = baralho_embaralhado_deck_carta.concat(deckId);
        String url = urlDeckId.concat("/draw/?count=" + total_carta);
        BaralhoEmbaralhadoDeckCartaResponse baralhoEmbaralhadoDeckCartaResponse = template.getForObject(url, BaralhoEmbaralhadoDeckCartaResponse.class);
        return baralhoEmbaralhadoDeckCartaResponse;
    }
}
