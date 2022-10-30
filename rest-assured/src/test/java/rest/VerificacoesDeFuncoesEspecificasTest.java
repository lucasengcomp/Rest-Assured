package rest;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VerificacoesDeFuncoesEspecificasTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveFazerVerificacaoDeIdadeMenorQue25Anos() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("age.findAll{it <= 25}.size()", is(2));
    }

    @Test
    public void deveFazerVerificacaoDeIdadeDe20Ate25Anos() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("age.findAll{it <= 25 && it > 20}.size()", is(1)); //it representa o objeto iterado
    }

    @Test
    @DisplayName("")
    public void deveFazerVerificacaoDeIdadeDe20Ate25AnosENomesDasPessoas() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistroComIdadeMenorIgualA25APartirDoNome() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("findAll{it.age <= 25}[0].name", is("Maria Joaquina"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistroComIdadeMenorIgualA25APartirDoUltimoRegistroNome() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("findAll{it.age <= 25}[-1].name", is("Ana Júlia"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistroComFind() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("find{it.age <= 25}.name", is("Maria Joaquina"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosQueContenhamLetraN() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina","Ana Júlia"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosQueContenhamTamanhoMaiorQue10() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("findAll{it.name.length() > 10}.name", hasItems("Maria Joaquina","João da Silva"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosComCollect() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("name.collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosComCollectEFind() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosComCollectEFindCondicaoBooleanE() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA"));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeRegistrosVerificandoUltimoId() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("id.max()", is(3));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeSalarioMinimoDaLista() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("salary.min()", is(1234.5678f));
    }

    @Test
    @DisplayName("")
    public void deveFazerBuscaDeSalarioMaximoDaLista() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("salary.max()", is(2500));
    }

    @Test
    @DisplayName("")
    public void deveFazerSomaDeSalariosDaLista() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5677, 0.001)));
    }

    @Test
    @DisplayName("")
    public void deveFazerSomaDeSalariosDaListaEntreMaiorEMenor() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("salary.findAll{it != null}.sum()", allOf(greaterThan(3000d), lessThan(5000d)));
    }


}
