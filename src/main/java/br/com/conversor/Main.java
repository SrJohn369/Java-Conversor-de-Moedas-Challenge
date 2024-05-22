package br.com.conversor;

import br.com.conversor.conectors.ConectorExchangeRate;
import br.com.conversor.cotacao.CotacaoAtual;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        ConectorExchangeRate conn = new ConectorExchangeRate("USD");
//        System.out.println(conn);
//        CotacaoAtual cota = new CotacaoAtual("BRL");
//        cota.comparaCom("USD");

        // MENU INTERATIVO
        while (true) {
            // inicar variáveis
            Scanner leia = new Scanner(System.in);
            String[] paridades = {"BRL", "USD", "GPB", "CNY", "CHF", "EUR"};

            System.out.println("""
                    ==================CONVERSOR DE MOEDAS================
                    |                                                   |
                    | ESCOLHA A MOEDA BASE E A MOEDA DE COTAÇÃO         |
                    | (Neste formato 2.1 -- essa escolha é USD/BRL)     |
                    |                                                   |
                    | 1.BRL(Real Brasileiro)    2.USD(Dólar Americano)  |
                    | 3.GPB(Libra Esterlina)    4.CNY(Yuan Renminb)     |
                    | 5.CHF(Franco suiço)       6.EUR(Euro)             |
                    | 99. Encerrar programa                             |
                    |                                                   |
                    =====================================================""");
            // Escolhas
            System.out.print("Escolha: ");
            double escolha = leia.nextDouble();
            System.out.println("=====================================================");
            //Finaliza o programa
            if (escolha == 99) {
                System.out.println("======  !!PROGRAMA FINALIZADO!!  =====");
                break;
            }
            // Separando escolhas em variáveis
            int moedaBase = (int) escolha;
            int moedaCotacao = ((int)Math.round((escolha - moedaBase)*10) - 1);

            switch (moedaBase) {
                // BRL BASE
                case 1:
                    System.out.printf("""
                    ==================CONVERSOR DE MOEDAS================
                    |                                                   |
                    | CONVERSOR BRL/%s ---                              |
                    | Para cada R$ 1 -> %s %2f                          |
                    | ===== / =====                                     |
                    | Mais FORTE contra: %s                             |
                    | |--> Para cada R$ 1 -> %s %2f                     |
                    | mais FRACO contra: %s                             |
                    |--> Para cada R$ 1 -> %s %2f                       |
                    =====================================================""");

                    // conversor
                    System.out.printf("\nDigite o valor em %s: ");
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                default:
                    break;
            }
        }
    }
}

