package com.api.service;

import com.api.client.BaralhoClient;
import com.api.model.BaralhoEmbaralhadoDeckCartaModel;
import com.api.model.BaralhoEmbaralhadoDeckModel;
import com.api.model.CardsModel;
import com.api.model.JogadorModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class BaralhoService {

    @Autowired
    private BaralhoClient baralhoService;

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

    public List<JogadorModel> startGame() {

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

        for (JogadorModel jogador : jogadorModel) {
            int soma = 0;
            soma = jogador.getValue().stream()
                    .filter(valor -> valor instanceof String)
                    .map(valor -> (String) valor)
                    .mapToInt(stringValor -> {
                        if (valorCartas.containsKey(stringValor)) {
                            // Se o valor existe no HashMap, retorna o valor correspondente.
                            return valorCartas.get(stringValor);
                        } else if (stringValor.matches("-?\\d+")) {
                            // Se o valor é um número, converte para inteiro.
                            return Integer.valueOf(stringValor);
                        } else {
                            return 0;
                        }
                    })
                    // Realizando a somatória.
                    .sum();

            // Setando a soma de cada deck de cartas.
            jogador.setTotal(soma);

            if (soma > maiorSoma) {
                maiorSoma = soma;
                jogadorGanhador = jogador;
            }
        }

        // Verificando a ganhador com a maior somatória. 
        JogadorModel jogadorGanhadorFinal = jogadorGanhador;
        jogadorModel.forEach(jogador -> {
            String status = (jogador == jogadorGanhadorFinal) ? "GANHOU :)" : "PERDEU :(";
            jogador.setStatus(status);
        });

        return jogadorModel;
    }
}
