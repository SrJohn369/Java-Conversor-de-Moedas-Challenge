package br.com.conversor.interacao;

import br.com.conversor.conectors.ConectorExchangeRate;
import br.com.conversor.cotacao.CotacaoAtual;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public final class Menu {
    // inicar variáveis
    Scanner leia = new Scanner(System.in);

    private ArrayList<Object> processaDados(int moedaBase, int moedaCotacao){
        // Vetor de identificação
        String[] paridades = {"BRL", "USD", "GPB", "CNY", "CHF", "EUR"};

        // inicia nossa variavel de dados
        ArrayList<Object> dadosProcessados = new ArrayList<>();

        // processando dados
        CotacaoAtual cota = new CotacaoAtual(paridades[moedaBase]);

        dadosProcessados.add(paridades[moedaBase]);
        dadosProcessados.add(paridades[moedaCotacao]);
        dadosProcessados.add(cota.valorCotaContra(paridades[moedaCotacao]));
        dadosProcessados.add(cota.forteContra(paridades[moedaBase]).getKey());
        dadosProcessados.add(cota.forteContra(paridades[moedaBase]).getValue());
        dadosProcessados.add(cota.fracoContra(paridades[moedaBase]).getKey());
        dadosProcessados.add(cota.fracoContra(paridades[moedaBase]).getValue());

        return dadosProcessados;
    }

    private void menuInicio(){
        System.out.println("""
                    ==================CONVERSOR DE MOEDAS================
                    |                                                   |
                    | ESCOLHA A MOEDA BASE E A MOEDA DE COTAÇÃO         |
                    | (Neste formato 2,1 -- essa escolha é USD/BRL)     |
                    |                                                   |
                    | 1.BRL(Real Brasileiro)    2.USD(Dólar Americano)  |
                    | 3.GPB(Libra Esterlina)    4.CNY(Yuan Renminb)     |
                    | 5.CHF(Franco suiço)       6.EUR(Euro)             |
                    | 99. Encerrar programa                             |
                    |                                                   |
                    =====================================================""");
    }

    private double menuParidades(int moedaBase, int moedaCotacao) {
        ArrayList<Object> dadosProntos = processaDados(moedaBase, moedaCotacao);

        // Tela dinâmica
        System.out.printf("""
                    ==================CONVERSOR DE MOEDAS================
                    |
                    | CONVERSOR %s/%s ---
                    | Para cada %s 1 -> %s %.2f
                    | ===== / =====
                    | Mais FORTE contra: %s
                    | |--> Para cada %s 1 -> %s %.2f
                    | mais FRACO contra: %s
                    |--> Para cada %s 1 -> %s %.2f
                    =====================================================""",
                dadosProntos.get(0),dadosProntos.get(1), dadosProntos.get(0),
                dadosProntos.get(1), (double)dadosProntos.get(2), dadosProntos.get(3),
                dadosProntos.get(0), dadosProntos.get(3), (double)dadosProntos.get(4),
                dadosProntos.get(5), dadosProntos.get(0), dadosProntos.get(5), (double)dadosProntos.get(6)
                );

        // conversor
        System.out.printf("\nDigite o valor em %s: ", dadosProntos.get(0));
        return leia.nextDouble();
    }

    public void inicia() {
        // MENU INTERATIVO
        while (true) {

        // Escolhas
        menuInicio();
        System.out.print("Escolha: ");
        double escolha = leia.nextDouble();
        System.out.println("=====================================================");
        //Finaliza o programa
        if (escolha == 99) {
            leia.close();
            System.out.println("======  !!PROGRAMA FINALIZADO!!  =====");
            break;
        }
        // Separando escolhas em variáveis
        int moedaBase = (int) (escolha - 1);
            System.out.println(moedaBase);
        int moedaCotacao = ((int)Math.round((escolha - (moedaBase + 1))*10) - 1); //doc
            System.out.println(moedaCotacao);

        switch (moedaBase) {
            // BRL BASE
            case 0:
                menuParidades(moedaBase, moedaCotacao);

            case 1:
                menuParidades(moedaBase, moedaCotacao);
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                break;
        }
    }
    }
}
