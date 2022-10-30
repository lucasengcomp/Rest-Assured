package rest;

import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BodyTest {

    @Test
    public void devoValidarRespostaBody() {
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
            .then()
            .statusCode(200)
            .body(Matchers.is("Ola Mundo!"));
    }

    @Test
    public void devoValidarSeBodyContemStringOlaMundo() {
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(Matchers.is("Ola Mundo!"));
    }

    @Test
    public void devoValidarSeBodyNaoContemNulo() {
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(is(not(nullValue())));
    }

    @Test
    public void devoValidarTodoBody() {
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(Matchers.is("Ola Mundo!"))
            .body(containsString("Mundo"))
            .body(is(not(nullValue())));
    }
}
