package rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class JsonSegundoNivelTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveVerificarCampoNomeDoSegundoNivel() {
        given()
        .when()
            .get(urlPath + "/2")
        .then()
            .statusCode(200)
            .body("endereco.rua", is("Rua dos bobos"));
    }

    @Test
    public void deveVerificarCampoNumeroDoSegundoNivel() {
        given()
        .when()
            .get(urlPath + "/2")
        .then()
            .statusCode(200)
            .body("endereco.numero", is(0));
    }
}
