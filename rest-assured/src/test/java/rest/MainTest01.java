package rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest01 {

    @Test
    public void testeOlaMundo() {
        var resposta = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");
        assertTrue(resposta.getBody().asString().equals("Ola Mundo!"));
        assertTrue(resposta.statusCode() == 200);
        assertTrue("O status code deveria ser 200", resposta.statusCode() == 200);
        assertEquals(200, resposta.statusCode());

        var validacao = resposta.then();
        validacao.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        var resposta = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");
        var validacao = resposta.then();
        validacao.statusCode(200);

        get("http://restapi.wcaquino.me/ola").then().statusCode(200);

        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200);
    }
}
