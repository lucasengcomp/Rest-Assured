package rest;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.*;

public class JsonPrimeiroNivelTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveVerificarPrimeiroNivelDoJson() {
        given()
        .when()
            .get(urlPath + "/1")
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("name", containsString("Silva"))
            .body("age", greaterThan(18));
    }

    @Test
    public void deveExtrairInformacoesDoPathDaRequisicao() {
        var resposta = request(Method.GET, urlPath + "/1");
        Assert.assertEquals(Integer.valueOf(1), resposta.path("id"));
    }

    @Test
    public void deveExtrairInformacoesDoJsonDaRequisicao() {
        var resposta = request(Method.GET, urlPath + "/1");
        JsonPath json = new JsonPath(resposta.asString());
        Assert.assertEquals(1, json.getInt("id"));
    }

    @Test
    public void deveExtrairInformacoesDoFromDaRequisicao() {
        var resposta = request(Method.GET, urlPath + "/1");
        int idExtraido = JsonPath.from(resposta.asString()).getInt("id");
        Assert.assertEquals(1, idExtraido);
    }
}
