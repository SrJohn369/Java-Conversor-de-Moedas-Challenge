package br.com.conversor.cotacao;

import br.com.conversor.calculo.CalculoConversor;
import com.google.gson.*;

import br.com.conversor.conectors.ConectorExchangeRate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CotacaoAtual implements CalculoConversor {
    // ATRIBUTOS --------------------
    private final String moedaBase;

    // CONSTRUTOR --------------------
    public CotacaoAtual(String moedaBase) {
        this.moedaBase = moedaBase;
    }

    // MÉTODOS ------------------------
    public double valorCotaContra(String moedaCotacao) {
        ConectorExchangeRate conn = new ConectorExchangeRate(moedaBase, moedaCotacao);

        // Capturar o json da request
        String jsonString = conn.respostaAPI().body();
        // Transformando num elemento
        JsonElement elementoJson = JsonParser.parseString(jsonString);
        // Transformar num obj
        JsonObject objJson = elementoJson.getAsJsonObject();

        return objJson.get("conversion_rate").getAsDouble();
    }

    private Map<String, Double> mapeia(ConectorExchangeRate conn) {
        // Capturar o json da request
        String jsonString = conn.respostaAPI().body();
        // Transformando num elemento
        JsonElement elementoJson = JsonParser.parseString(jsonString);
        // Transformar num obj
        JsonObject objJson = elementoJson.getAsJsonObject();
        // Pega o array dentro do Json
        JsonObject conversionRates = objJson.getAsJsonObject("conversion_rates");

        // Convertendo the JsonObject to a Map
        Map<String, Double> ratesMap = new HashMap<>();
        for (String key : conversionRates.keySet()) {
            ratesMap.put(key, conversionRates.get(key).getAsDouble());
        }

        return ratesMap;
    }

    // Forte contra
    public Map.Entry<String, Double> forteContra(String moedaBase){
        ConectorExchangeRate conn = new ConectorExchangeRate(moedaBase);

        Map<String, Double> ratesMap = mapeia(conn);

        return Collections.max(ratesMap.entrySet(), Map.Entry.comparingByValue());
    }

    // Fraco Contra
    public Map.Entry<String, Double> fracoContra(String moedaBase){
        ConectorExchangeRate conn = new ConectorExchangeRate(moedaBase);

        Map<String, Double> ratesMap = mapeia(conn);

        return Collections.min(ratesMap.entrySet(), Map.Entry.comparingByValue());
    }

    @Override
    public double converteValor(String moedaCotacao, double valorMoedaBase) {
        double cotacao = valorCotaContra(moedaCotacao);

        return cotacao * valorMoedaBase;
    }
}
