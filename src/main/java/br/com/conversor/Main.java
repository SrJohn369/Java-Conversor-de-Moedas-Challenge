package br.com.conversor;

import br.com.conversor.conectors.ConectorExchangeRate;
import br.com.conversor.cotacao.CotacaoAtual;

public class Main {
    public static void main(String[] args) {
        ConectorExchangeRate conn = new ConectorExchangeRate("USD");
        System.out.println(conn);
        CotacaoAtual cota = new CotacaoAtual("BRL");
        cota.comparaCom("USD");
    }
}

