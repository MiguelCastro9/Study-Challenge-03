package com.api.model;

import java.util.List;

/**
 *
 * @author Miguel Castro
 */
public class JogadorModel {

    private String deck_id;

    private List<String> value;
    
    private Integer total;
    
    private String status;

    public JogadorModel() {
    }

    public JogadorModel(String deck_id, List<String> value) {
        this.deck_id = deck_id;
        this.value = value;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
