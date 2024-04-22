package com.nexign.testUserService.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {
    @Test
    void test() throws IOException {
        given()
                .baseUri("/v1/api/user")
                .when().get()
                .then().statusCode(200);
    }
}
