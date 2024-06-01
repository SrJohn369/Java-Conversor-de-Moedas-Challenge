<h1 align="center">
  CHALLENGE JAVA <img width="96" height="96" src="https://img.icons8.com/color/96/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1"/>
</h1>  

<h2 align="center">
  Conversor de moedas
</h2>
   
<h4 align="center">  
  
  ![Concluído](http://img.shields.io/static/v1?label=&message=CONCLUÍDO&color=GREEN&style=for-the-badge)
  ![GitHub Tag](https://img.shields.io/github/v/tag/SrJohn369/Java-Conversor-de-Moedas-Challenge?style=for-the-badge&label=Version)
</h4>  
  
#### DESCRIÇÃO
App acessado via terminal usado para converter moedas como USD -> BRL utilizando uma API ExchangeRate-API externa para receber os valores das cotações em tempo real. Escolhendo as paridades através dos números de 1 a 6 no formato `2,5` por exemplo, é possível converter valores entre estas paridades que são "BRL", "USD", "GBP", "CNY", "CHF" e "EUR".  
#### BREVE DEMONSTRAÇÃO
![C__Windows_System32_cmd exe 2024-05-27 00-22-19](https://github.com/SrJohn369/Java-Conversor-de-Moedas-Challenge/assets/106630200/d68773b8-4e1d-4462-8394-ee8cdeb8024f)  
  
---  
### TECNOLOGIAS  
- `Java21`
- `InteliJ IDEA`
- `S.O. Windows 10`  
---  
### DOCUMENTAÇÃO :books:  
----  
CLASSES E INTERFACE    
`Class Main`  
`Class Menu`  
`Class CotacaoAtual`  
`Class ConectorExchangeRate`  
`Interface CalculoConversor`  

---
#### CLASS Main  
A classe é usada para iniciar a aplicação. Uma instância `menu` da classe `Menu` usando a função inicia()  
  
FUNÇÕES  
  > main()  
  O que faz: Inicia a aplicação
  Modificador de acesso: `public`  
  Parâmetros: `String[] args`  
> Retorno: `void`  
  <hr>

#### CLASS Menu
A classe é usada para gerar o menu e rodar a aplicação  
  
FUNÇÕES  
  > clearConsole()  
  O que faz: Limpa tela do console  
  Modificador de acesso: `private`  
  Retorno: `void`  

  > menuInicio()  
  O que faz: Exibe o menu de inicio  
  Modificador de acesso: `private`  
  Retorno: `void`  

  > menuConversor()  
  O que faz: Processa dados e Exibe resultados  
  Modificador de acesso: `private`  
  Parâmetros: int moedaBaseId, int moedaCotacaoId  
  Retorno: `void`

  > inicia()  
  O que faz: Inicia o aplicativo  
  Modificador de acesso: `public`  
  Retorno: `void`  
  <hr>  
  
#### CLASS CotacaoAtual
Classe usada para trazer as cotas e fazer o calculo de cotação por meio da impementação da interface CalculoConversor  

ATRIBUTOS  
> moedaBase: String

CONSTRUTORES  
Usado para receber um valor necessário para as consultas e calculos, a moeda base para cotação
```java
public CotacaoAtual(String moedaBase) {
        this.moedaBase = moedaBase;
    }
```  
FUNÇÕES
> valorCotaContra()  
  O que faz: Consulta dados na API ExenchangeRate-API e retorna o valor da cotação da moeda base contra a moeda de cotação  
  Modificador de acesso: `public`  
  Parâmetros: String moedaCotacao  
  Retorno: `double`

> mapeia()  
  O que faz: Mapea todos os valores de um objeto JSON da consulta da moeda base feita pela classe ConectorExchangeRate usando o método respostaAPI() retornado um `Map<String, Double>`  
  Modificador de acesso: `private`  
  Retorno: `Map<String, Double>`
  Parâmetros: ConectorExchangeRate conn  
  Code:
  ```java
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
  ```  
> forteContra()  
  O que faz: Usa a função `mapeia()` para fazer a filtragem e capturar a moeda mais forte contra a moeda base retornado um `Map<String, Double>`  
  Modificador de acesso: `public`  
  Retorno: `Map<String, Double>`
  Parâmetros: String moedaBase  
  Code:
  ```java
 // Forte contra
    public Map.Entry<String, Double> forteContra(String moedaBase){
        ConectorExchangeRate conn = new ConectorExchangeRate(moedaBase);

        Map<String, Double> ratesMap = mapeia(conn);

        return Collections.max(ratesMap.entrySet(), Map.Entry.comparingByValue());
    }
```
---

### INSTALAÇÃO
### RODANDO PROJETO
### FUNÇÕES
### DEV
