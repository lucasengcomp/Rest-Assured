package rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonListalTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveVerificarTamanhoDaLista() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .body("filhos", hasSize(2));
    }

    @Test
    public void deveVerificarPrimeiroItemDaLista() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .body("filhos[0].name", is("Zezinho"));
    }

    @Test
    public void deveVerificarSegundoItemDaLista() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .body("filhos[1].name", is("Luizinho"));
    }

    @Test
    public void deveVerificarSeExisteItemNaLista() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .body("filhos.name", hasItem("Luizinho"));
    }

    @Test
    public void deveVerificarSeExisteItensNaLista() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .body("filhos.name", hasItems("Luizinho", "Zezinho"));
    }
}
