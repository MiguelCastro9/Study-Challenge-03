package com.api.model;

import java.util.List;

/**
 *
 * @author Miguel Castro
 */
public class BaralhoEmbaralhadoDeckCartaModel {

    private String success;

    private String deck_id;

    private List<CardsModel> cards;

    private String remaining;

    public BaralhoEmbaralhadoDeckCartaModel() {
    }

    public BaralhoEmbaralhadoDeckCartaModel(String success, String deck_id, List<CardsModel> cards, String remaining) {
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

    public List<CardsModel> getCards() {
        return cards;
    }

    public void setCards(List<CardsModel> cards) {
        this.cards = cards;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "BaralhoEmbaralhadoDeckCartaModel{" + "success=" + success + ", deck_id=" + deck_id + ", cards=" + cards + ", remaining=" + remaining + '}';
    }
    
    
}
