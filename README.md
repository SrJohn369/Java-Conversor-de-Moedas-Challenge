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

## Índice
- [DESCRIÇÃO](#DESCRIÇÃO)
- [TECNOLOGIAS](#TECNOLOGIAS)
- [INSTALANDO & RODANDO](#INSTALANDO-&-RODANDO)
- [DOCUMENTAÇÃO](#DOCUMENTAÇÃO)
  - [CLASSES E INTERFACE](#CLASSES-E-INTERFACE)
  - [CLASS Main](#CLASS-Main)
  - [Class Menu](#Class-Menu)
  - [Class CotacaoAtual](#Class-CotacaoAtual)
  - [Class ConectorExchangeRate](#Class-ConectorExchangeRate)
  - [Interface CalculoConversor](#Interface-CalculoConversor)
---
  
#### DESCRIÇÃO :bookmark_tabs:
App acessado via terminal usado para converter moedas como USD -> BRL utilizando uma API ExchangeRate-API externa para receber os valores das cotações em tempo real. Escolhendo as paridades através dos números de 1 a 6 no formato `2,5` por exemplo, é possível converter valores entre estas paridades que são "BRL", "USD", "GBP", "CNY", "CHF" e "EUR".  
#### BREVE DEMONSTRAÇÃO
![C__Windows_System32_cmd exe 2024-05-27 00-22-19](https://github.com/SrJohn369/Java-Conversor-de-Moedas-Challenge/assets/106630200/d68773b8-4e1d-4462-8394-ee8cdeb8024f)  
  
---  
### TECNOLOGIAS :hammer:
- `Java21`
- `InteliJ IDEA`
- `S.O. Windows 10`  
---  
### INSTALANDO & RODANDO 
  
Esta aplicação Java é feita com Maven(mvn).  
Então certifique-se de ter mvn instalado na sua máquina  
```bash
mvn -version
```
  
Certifique-se de ter esta config de plugin no seu pom.xml para executar a aplicação.
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>3.0.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>java</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <mainClass>br.com.conversor.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Com tudo ok, execute o comando abaixo
```bash
mvn exec:java
```
  
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
  Code:
```java
private static void clearConsole() {
        // Usar códigos de escape ANSI para portabilidade
        System.out.print("\033[H\033[2J");
    }
```

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
> private final moedaBase: String

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
  O que faz: Usa a função `mapeia()` para fazer a filtragem e capturar a moeda mais FORTE contra a moeda base retornado um `Map<String, Double>`  
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
> fracoContra()  
  O que faz: Usa a função `mapeia()` para fazer a filtragem e capturar a moeda mais FRACA contra a moeda base retornado um `Map<String, Double>`  
  Modificador de acesso: `public`  
  Retorno: `Map<String, Double>`
  Parâmetros: String moedaBase  
  Code:
  ```java
// Fraco Contra
    public Map.Entry<String, Double> fracoContra(String moedaBase){
        ConectorExchangeRate conn = new ConectorExchangeRate(moedaBase);

        Map<String, Double> ratesMap = mapeia(conn);

        return Collections.min(ratesMap.entrySet(), Map.Entry.comparingByValue());
    }

```
> converteValor()  
  O que faz: Conteverte o valor fornecido de cotação multiplicando pela moeda base e retornado um `double`  
  Modificador de acesso: `public`  
  Retorno: `double`
  Parâmetros: String moedaCotacao, double valorMoedaBase  
  Code:
  ```java
 @Override
    public double converteValor(String moedaCotacao, double valorMoedaBase) {
        double cotacao = valorCotaContra(moedaCotacao);

        return cotacao * valorMoedaBase;
    }
```
#### CLASS ConectorExchangeRate   
Esta classe irá tratar das requisições e conexão com a API  
ATRIBUTOS  
> private final key: String  
> private code: String  
> private uri: String
  
CONSTRUTORES  
Todos os construtores irão crirar uma URI dinânmica  
`ConectorExchangeRate()`  
Este contrutor irá criar uma uri automatica de conexão que irá capturar todos os códigos disponíveis da API  
```java
    public ConectorExchangeRate() {
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + code;
    }
```  
`ConectorExchangeRate(String code)`  
Este contrutor irá criar uma uri automatica de conexão que irá capturar totodos dados de uma moeda  
```java
public ConectorExchangeRate(String code) {
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + code;
    }
```
`ConectorExchangeRate(String code1, String code2)`  
Este contrutor irá criar uma uri automatica de conexão que irá capturar dados de uma moeda contra outra moeda, uma moeda base e uma moeda de cotação ex: USD/BRL  
```java
public ConectorExchangeRate(String code1, String code2){
        this.uri = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + code1 + "/" + code2;
    }
```

FUNÇÕES
> respostaAPI()  
  O que faz: Faz a conexão com a API e retorna uma resposta JSON em STR   
  Modificador de acesso: `public`  
  Retorno: `HttpResponse<String>`  
  Code:
```java
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

```
#### Interface CalculoConversor
Tratará de garantir que a função de calculo `converteValor(String moedaCotacao, double valorMoedaBase)` esja implementada  
CODE:  
```java
public interface CalculoConversor {
    double converteValor(String moedaCotacao, double valorMoedaBase);
}
```
---
### DEV :man_technologist:
