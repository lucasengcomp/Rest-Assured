package rest;

import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListasTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveBuscarUmaListaDeUsersComSucesso() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200);
    }

    @Test
    public void deveBuscarUmaListaRaiz() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("$", hasSize(3));
    }

    @Test
    public void deveBuscarDadosDoAtributoNomeDaListaRaiz() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"));
    }

    @Test
    public void deveBuscarDadosDoAtributoIdadeDaListaRaiz() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("age[1]", is(25));
    }

    @Test
    public void deveBuscarDadosDoAtributoSalarioDaListaRaiz() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("salary", contains(1234.5678f, 2500, null));
    }

    @Test
    public void deveBuscarDadosDeUmaListaDentroDeOutraLista() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")));
    }
}
