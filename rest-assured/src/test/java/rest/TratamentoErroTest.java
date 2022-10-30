package rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TratamentoErroTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveRetornar404QuandoUsuarioNaoForEncontrado() {
        given()
        .when()
            .get(urlPath + "/4")
        .then()
            .statusCode(404);
    }

    @Test
    public void deveRetornarMensagemDeErroQuandoUsuarioNaoForEncontrado() {
        given()
        .when()
            .get(urlPath + "/4")
        .then()
            .body("error", is("Usuário inexistente"));
    }
}
