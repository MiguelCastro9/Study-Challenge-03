package com.api.service;

import com.api.client.BaralhoClient;
import com.api.response.BaralhoEmbaralhadoDeckCartaResponse;
import com.api.response.BaralhoEmbaralhadoDeckResponse;
import com.api.response.CardsResponse;
import com.api.model.JogadorModel;
import com.api.repository.JogadorRepository;
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
    
    @Autowired
    private JogadorRepository jogadorRepository;

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

        List<BaralhoEmbaralhadoDeckResponse> baralhoEmbaralhadoDeckResponse = new ArrayList<>();
        List<BaralhoEmbaralhadoDeckCartaResponse> baralhoEmbaralhadoDeckCartaResponse = new ArrayList<>();
        List<JogadorModel> jogadorModel = new ArrayList<>();

        // Adicionando decks embaralhados em quatro mãos com cinco cartas cada uma. 
        for (int i = 0; i < total_maos; i++) {
            baralhoEmbaralhadoDeckResponse.add(baralhoService.apiBaralhoEmbaralhadoDeck());
            baralhoEmbaralhadoDeckCartaResponse.add(baralhoService.apiBaralhoEmbaralhadoDeckCarta(baralhoEmbaralhadoDeckResponse.get(i).getDeck_id()));
        }

        // Recuperando o ID do deck e os valores das cartas e adicionando no HashMap.
        Map<String, List<String>> deckIdValues = new HashMap<>();
        for (int i = 0; i < baralhoEmbaralhadoDeckCartaResponse.size(); i++) {
            String deckId = baralhoEmbaralhadoDeckCartaResponse.get(i).getDeck_id();
            List<String> values = deckIdValues.getOrDefault(deckId, new ArrayList<>());
            for (CardsResponse card : baralhoEmbaralhadoDeckCartaResponse.get(i).getCards()) {
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
        List<JogadorModel> jogadoresEmpate = new ArrayList<>();

        for (JogadorModel jogador : jogadorModel) {
            int soma = jogador.getValue().stream()
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
                jogadoresEmpate.clear(); // Limpa a lista.
                jogadoresEmpate.add(jogador);
            } else if (soma == maiorSoma) {
                jogadoresEmpate.add(jogador);
            }
        }

        // Verificando se houve empate.
        if (jogadoresEmpate.size() > 1) {
            // Empate
            for (JogadorModel jogador : jogadorModel) {
                jogador.setStatus("EMPATE :/");
            }
        } else {
            // Verificando o ganhador com a maior somatória.
            JogadorModel jogadorGanhadorFinal = jogadoresEmpate.get(0);
            for (JogadorModel jogador : jogadorModel) {
                String status = (jogador == jogadorGanhadorFinal) ? "GANHOU :)" : "PERDEU :(";
                jogador.setStatus(status);
            }
        }
        
        for (int i = 0; i < jogadorModel.size(); i++) {
            jogadorRepository.save(jogadorModel.get(i));
        }
        
        return jogadorModel;
    }
}
