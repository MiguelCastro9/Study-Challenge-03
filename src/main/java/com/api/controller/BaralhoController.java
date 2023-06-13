package com.api.controller;

import com.api.model.BaralhoEmbaralhadoDeck;
import com.api.service.BaralhoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Miguel Castro
 */
@RestController
public class BaralhoController {

    @Autowired
    private BaralhoService cartaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BaralhoEmbaralhadoDeck>> getCartas() {
        
        List<BaralhoEmbaralhadoDeck> baralhoEmbaralhadoDeck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            baralhoEmbaralhadoDeck.add(cartaService.apiBaralhoEmbaralhadoDeck());
        }
        
        return new ResponseEntity<List<BaralhoEmbaralhadoDeck>>(baralhoEmbaralhadoDeck, HttpStatus.OK);
    }
}
