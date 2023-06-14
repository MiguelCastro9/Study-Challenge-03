package com.api.controller;

import com.api.model.JogadorModel;
import com.api.service.BaralhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Baralho")
public class BaralhoController {

    @Autowired
    BaralhoService baralhoService;

    @Operation(description = "Start Game")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JogadorModel>> startGame() {

        List<JogadorModel> jogadorModel = baralhoService.startGame();
        return new ResponseEntity<List<JogadorModel>>(jogadorModel, HttpStatus.OK);
    }
}
