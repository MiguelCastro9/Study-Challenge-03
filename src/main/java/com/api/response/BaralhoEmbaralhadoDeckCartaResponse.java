package com.api.response;

import java.util.List;

/**
 *
 * @author Miguel Castro
 */
public class BaralhoEmbaralhadoDeckCartaResponse {

    private String success;

    private String deck_id;

    private List<CardsResponse> cards;

    private String remaining;

    public BaralhoEmbaralhadoDeckCartaResponse() {
    }

    public BaralhoEmbaralhadoDeckCartaResponse(String success, String deck_id, List<CardsResponse> cards, String remaining) {
        this.success = success;
        this.deck_id = deck_id;
        this.cards = cards;
        this.remaining = remaining;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<CardsResponse> getCards() {
        return cards;
    }

    public void setCards(List<CardsResponse> cards) {
        this.cards = cards;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }
}
