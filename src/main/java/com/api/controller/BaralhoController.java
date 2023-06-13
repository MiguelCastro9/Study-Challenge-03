package com.api.controller;

import com.api.model.BaralhoEmbaralhadoDeckCartaModel;
import com.api.model.BaralhoEmbaralhadoDeckModel;
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
    private BaralhoService baralhoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BaralhoEmbaralhadoDeckCartaModel>> getCartas() {

        List<BaralhoEmbaralhadoDeckModel> baralhoEmbaralhadoDeckModel = new ArrayList<>();
        List<BaralhoEmbaralhadoDeckCartaModel> baralhoEmbaralhadoDeckCartaModel = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            baralhoEmbaralhadoDeckModel.add(baralhoService.apiBaralhoEmbaralhadoDeck());
            baralhoEmbaralhadoDeckCartaModel.add(baralhoService.apiBaralhoEmbaralhadoDeckCarta(baralhoEmbaralhadoDeckModel.get(i).getDeck_id()));
        }

        return new ResponseEntity<List<BaralhoEmbaralhadoDeckCartaModel>>(baralhoEmbaralhadoDeckCartaModel, HttpStatus.OK);
    }
}
