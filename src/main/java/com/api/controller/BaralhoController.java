package com.api.controller;

import com.api.model.BaralhoEmbaralhadoDeckCartaModel;
import com.api.model.BaralhoEmbaralhadoDeckModel;
import com.api.model.CardsModel;
import com.api.model.JogadorModel;
import com.api.service.BaralhoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${total.maos}")
    private Integer total_maos;

    @Value("${valor.ace}")
    private Integer valor_ace;

    @Value("${valor.jack}")
    private Integer valor_jack;

    @Value("${valor.queen}")
    private Integer valor_queen;

    @Value("${valor.king}")
    private Integer valor_king;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JogadorModel>> startGame() {

        List<BaralhoEmbaralhadoDeckModel> baralhoEmbaralhadoDeckModel = new ArrayList<>();
        List<BaralhoEmbaralhadoDeckCartaModel> baralhoEmbaralhadoDeckCartaModel = new ArrayList<>();
        List<JogadorModel> jogadorModel = new ArrayList<>();

        // Adicionando decks embaralhados em quatro mãos com cinco cartas cada uma. 
        for (int i = 0; i < total_maos; i++) {
            baralhoEmbaralhadoDeckModel.add(baralhoService.apiBaralhoEmbaralhadoDeck());
            baralhoEmbaralhadoDeckCartaModel.add(baralhoService.apiBaralhoEmbaralhadoDeckCarta(baralhoEmbaralhadoDeckModel.get(i).getDeck_id()));
        }

        // Recuperando o ID do deck e os valores das cartas e adicionando no HashMap.
        Map<String, List<String>> deckIdValues = new HashMap<>();
        for (int i = 0; i < baralhoEmbaralhadoDeckCartaModel.size(); i++) {
            String deckId = baralhoEmbaralhadoDeckCartaModel.get(i).getDeck_id();
            List<String> values = deckIdValues.getOrDefault(deckId, new ArrayList<>());
            for (CardsModel card : baralhoEmbaralhadoDeckCartaModel.get(i).getCards()) {
                values.add(card.getValue());
            }
            deckIdValues.put(deckId, values);
        }

        // Adicionando os valores do HashMap na classe modelo.
        for (Map.Entry<String, List<String>> entry : deckIdValues.entrySet()) {
            String deckId = entry.getKey();
            List<String> values = entry.getValue();
            jogadorModel.add(new JogadorModel(deckId, values));
        }

        // Criando um HashMap para adicionar os valores de cartas especiais.
        Map<String, Integer> valorCartas = new HashMap<>();
        valorCartas.put("ACE", valor_ace);
        valorCartas.put("JACK", valor_jack);
        valorCartas.put("QUEEN", valor_queen);
        valorCartas.put("KING", valor_king);

        int maiorSoma = 0;
        JogadorModel jogadorGanhador = null;
        
        // Converterndo valor da carta para 'int' e realizando a somatória.
        for (JogadorModel jogador : jogadorModel) {
            int soma = 0;
            for (Object valor : jogador.getValue()) {
                if (valor instanceof String) {
                    String stringValor = (String) valor;
                    if (valorCartas.containsKey(stringValor)) {
                        int numero = valorCartas.get(stringValor);
                        soma += numero;
                    } else if (stringValor.matches("-?\\d+")) {
                        int numero = Integer.parseInt(stringValor);
                        soma += numero;
                    }
                }
            }

            // Setando a soma de cada deck de cartas.
            jogador.setTotal(soma);

            if (soma > maiorSoma) {
                maiorSoma = soma;
                jogadorGanhador = jogador;
            }
        }
        
        // Verificando a ganhador com a maior somatória. 
        for (JogadorModel jogador : jogadorModel) {
            if (jogador == jogadorGanhador) {
                jogador.setStatus("GANHOU :)");
            } else {
                jogador.setStatus("PERDEU :(");
            }
        }

        return new ResponseEntity<List<JogadorModel>>(jogadorModel, HttpStatus.OK);
    }

}
