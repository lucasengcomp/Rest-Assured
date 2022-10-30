package rest;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class JuncaoJsonPathComJavaTest {

    String urlPath = "https://restapi.wcaquino.me/users";

    @Test
    public void deveVerificarTamanhoDoRegistro() {

        ArrayList<String> nomes =
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .extract()
            .path("name.findAll{it.startsWith('Maria')}");

        Assert.assertEquals(1, nomes.size());
    }

    @Test
    public void deveVerificarCampoDoRegistroMaiusculo() {

        ArrayList<String> nomes =
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .extract()
            .path("name.findAll{it.startsWith('Maria')}");

        Assert.assertTrue(nomes.get(0).equalsIgnoreCase("mAria jOaQuInA"));
        Assert.assertEquals(nomes.get(0).toUpperCase(), "mAria jOaQuInA".toUpperCase());
    }

}
