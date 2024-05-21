package br.com.conversor.conectors;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConectorExchangeRate {
    // ATRIBUTOS --------------------
    private final String key = "99a7c9a0ab4c6fcaf2e0e114";
    private String code = "codes";
    private String uri;
    // CONSTRUTORES --------------------
    // para capturar todos os cod
    public ConectorExchangeRate() {
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + code;
    }
    // para todas as cotações da moeda solicitada
    public ConectorExchangeRate(String code) {
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + code;
    }
    //para fazer comparaçõa de moedas
    public ConectorExchangeRate(String code1, String code2){
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + code1 + "/" + code2;
    }

    // GETTERS & SETTERS --------------------
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // MÉTODOS ------------------------
    public HttpResponse<String> respostaAPI() {
        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        // Fazer requisição GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.uri))
                .GET()
                .build();
        try {
            // capturando resposta
            return client.send(request, BodyHandlers.ofString());
        } catch (Exception err) {
            System.out.println("Houve um erro na requisição: " + err);
            return null;
        }
    }

    @Override
    public String toString() {
        return "URI: " + respostaAPI().uri()
                + "\nCorpo da requisição: " + "\n" + respostaAPI().body() + "\n";
    }
}
