package br.com.conversor.interacao;

import br.com.conversor.cotacao.CotacaoAtual;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Menu {
    // inicar variáveis
    Scanner leia = new Scanner(System.in);

    private void menuInicio(){
        System.out.println("""
                    ==================CONVERSOR DE MOEDAS================
                    |                                                   |
                    | ESCOLHA A MOEDA BASE E A MOEDA DE COTAÇÃO         |
                    | (Neste formato 2,1 -- essa escolha é USD/BRL)     |
                    |                                                   |
                    | 1.BRL(Real Brasileiro)    2.USD(Dólar Americano)  |
                    | 3.GBP(Libra Esterlina)    4.CNY(Yuan Renminbi)    |
                    | 5.CHF(Franco suiço)       6.EUR(Euro)             |
                    | 99. Encerrar programa                             |
                    |                                                   |
                    =====================================================""");
    }

    private void menuConversor(int moedaBaseId, int moedaCotacaoId) {
        // Vetor de identificação
        String[] paridadesCodes = {"BRL", "USD", "GBP", "CNY", "CHF", "EUR"};
        String[] paridadesNomes = {"Real", "Dólar", "Libra", "Yuan", "Franco", "Euro"};

        // processando dados
        System.out.println("Processando....");
        CotacaoAtual cota = new CotacaoAtual(paridadesCodes[moedaBaseId]);

        var moedaBase = paridadesCodes[moedaBaseId];
        var moedaCotacao = paridadesCodes[moedaCotacaoId];
        var moedaBaseNome = paridadesNomes[moedaBaseId];
        var moedaCotacaoNome = paridadesNomes[moedaCotacaoId];
        var valorCotaContra = cota.valorCotaContra(paridadesCodes[moedaCotacaoId]);
        var forteContraParidade = cota.forteContra(paridadesCodes[moedaBaseId]).getKey();
        var forteContraCota = cota.forteContra(paridadesCodes[moedaBaseId]).getValue();
        var fracoContraParidade = cota.fracoContra(paridadesCodes[moedaBaseId]).getKey();
        var fracoContraCota = cota.fracoContra(paridadesCodes[moedaBaseId]).getValue();

        // Tela dinâmica
        System.out.printf("""
                    ==================CONVERSOR DE MOEDAS================
                    |
                    | CONVERSOR %s/%s --- Converter %s para %s
                    | --------------
                    | Para cada %s 1 -> %s %.2f
                    | --------------
                    | Mais FORTE contra: %s
                    | |--> Para cada %s 1 -> %s %.2f
                    | Mais FRACO contra: %s
                    | |--> Para cada %s 1 -> %s %.2f
                    =====================================================""",
                moedaBase, moedaCotacao, moedaBaseNome, moedaCotacaoNome, moedaBase,
                moedaCotacao, valorCotaContra, forteContraParidade,
                moedaBase, forteContraParidade, forteContraCota,
                fracoContraParidade, moedaBase, fracoContraParidade, fracoContraCota
                );

        // conversor
        System.out.printf("\nDigite o valor em %s: ", moedaBase);
        double paraConveter = leia.nextDouble();
        System.out.println("===========\nProcessando....");

        System.out.printf("""
                ==================CONVERSOR DE MOEDAS================
                |               RESULTADO DA CONVERSÃO
                |       %s  %.2f    ---->>    %s  %.2f
                =====================================================
                """, moedaBase, paraConveter, moedaCotacao,
                cota.converteValor(moedaCotacao, paraConveter)
        );
        System.out.println("PRESSIONE ENTER PARA CONTINUAR...");
        leia.nextLine(); // remove newLine residual
        leia.nextLine();
    }

    public void inicia() {
        // MENU INTERATIVO
        while (true) {
            try {
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
                int moedaCotacao = ((int) Math.round((escolha - (moedaBase + 1)) * 10) - 1); //doc

                switch (moedaBase) {
                    // BRL BASE
                    case 0:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    case 1:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    case 2:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    case 3:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    case 4:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    case 5:
                        menuConversor(moedaBase, moedaCotacao);
                        break;
                    default:
                        System.out.println("VALOR INVÁLIDO - TENTE NOVAMENTE");
                        break;
                }
            } catch (InputMismatchException err) {
                System.out.println("=====================================================");
                System.out.println("VALOR INVÁLIDO - TENTE NOVAMENTE");
                leia.nextLine(); // Consome a entrada inválida e o caractere de nova linha
            }
        }
    }
}
