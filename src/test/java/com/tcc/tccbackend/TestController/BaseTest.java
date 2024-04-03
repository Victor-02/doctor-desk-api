package com.tcc.tccbackend.TestController;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class BaseTest {
    public BaseTest(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    public static String getJWT() {
        return given()
                .body("{\n" + "\t\"email\": \"paula@gmail.com\",\n" + "\t\"senha\": \"senha\"\n" + "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/user/login")
                .then()
                .extract()
                .path("token");
    }
}
