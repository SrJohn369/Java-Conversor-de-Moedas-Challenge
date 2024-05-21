package br.com.conversor.cotacao;

import com.google.gson.*;

import br.com.conversor.conectors.ConectorExchangeRate;

public class CotacaoAtual extends ConectorExchangeRate {
    // ATRIBUTOS --------------------
    private final String moeda;

    // CONSTRUTOR --------------------
    public CotacaoAtual(String moeda) {
        this.moeda = moeda;
    }

    // MÉTODOS ------------------------
    public void comparaCom(String outraMoeda) {
        ConectorExchangeRate conn = new ConectorExchangeRate(moeda, outraMoeda);
        System.out.println(conn);

        // Capturar o json da request
        String jsonString = conn.respostaAPI().body();
        // Transformando num elemento
        JsonElement elementoJson = JsonParser.parseString(jsonString);
        // Transformar num obj
        JsonObject objJson = elementoJson.getAsJsonObject();

    }

}
