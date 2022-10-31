package rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class XmlTest {

    static String urlPath;
    String recurso = "user";

    public RequestSpecification requestSpecification;
    public ResponseSpecification responseSpecification;


    @Before
    public void setUp() {
        urlPath = "https://restapi.wcaquino.me/usersXML";

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.log(LogDetail.ALL);
        requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        responseSpecification = resBuilder.build();
    }

    @Test
    public void deveCriarLogDosTestes() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.log(LogDetail.ALL);
    }

    @Test
    public void deveVerificarCampoNome() {
        given()
            .spec(requestSpecification)
        .when()
            .get(urlPath + "/3")
        .then()
            .spec(responseSpecification)
            .statusCode(200)
            .rootPath(recurso)
            .body("name", is("Ana Julia"));
    }

    @Test
    public void deveVerificarCampoId() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .rootPath(recurso)
            .body("@id", is("3"));
    }

    @Test
    public void deveVerificarTamanhoCampoNome() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .rootPath(recurso)
            .body("filhos.name.size()", is(2));
    }

    @Test
    public void deveVerificarPosicaoCampoNome() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .rootPath(recurso)
            .body("filhos.name[0]", is("Zezinho"))
            .body("filhos.name[1]", is("Luizinho"));
    }

    @Test
    public void deveVerificarSeExisteDadosNoCampoNome() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .rootPath(recurso)
            .body("filhos.name", hasItems("Luizinho", "Zezinho"))
            .body("filhos.name", hasItem("Luizinho"));
    }

    @Test
    public void deveVerificarSeExisteDadosNoCampoNomeAdicionandoRootPath() {
        given()
        .when()
            .get(urlPath + "/3")
        .then()
            .statusCode(200)
            .rootPath(recurso + ".filhos")
            .detachRootPath("filhos")
            .body("filhos.name", hasItems("Luizinho", "Zezinho"))
            .appendRootPath("filhos")
            .body("name", hasItem("Luizinho"));
    }

    @Test
    public void deveFazerContagemDeQuantosRegistrosUserForamRetornados() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("users.user.size()", is(3));
    }

    @Test
    public void deveFazerContagemDeQuantosRegistrosUserTem25Anos() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("users.user.findAll{it.age.toInteger() <= 25}.size()", is(2));
    }

    @Test
    public void deveFazerVerificarNomeDeUserQueTem25Anos() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("users.user.find{it.age == 25}.name", is("Maria Joaquina"));
    }

    @Test
    public void deveVerificarIdDisponivel() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("users.user.@id", hasItems("1", "2", "3"));
    }

    @Test
    public void deveBuscarPorNomeEComparar() {
        given()
        .when()
            .get(urlPath)
        .then()
            .statusCode(200)
            .body("users.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}", is("MARIA JOAQUINA"));
    }
}
