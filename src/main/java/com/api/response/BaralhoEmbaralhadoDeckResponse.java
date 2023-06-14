package com.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Miguel Castro
 */
@Schema(name = "Baralho Embaralhado Deck - response")
public class BaralhoEmbaralhadoDeckResponse {

    private String success;

    private String deck_id;

    private String remaining;

    private String shuffled;

    public BaralhoEmbaralhadoDeckResponse() {
    }

    public BaralhoEmbaralhadoDeckResponse(String success, String deck_id, String remaining, String shuffled) {
        this.success = success;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.shuffled = shuffled;
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

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getShuffled() {
        return shuffled;
    }

    public void setShuffled(String shuffled) {
        this.shuffled = shuffled;
    }
}
