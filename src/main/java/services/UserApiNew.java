package services;

import dto.UserNew;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApiNew {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String USER = "/user";
    private RequestSpecification rspec;

    public UserApiNew(){
        rspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser(UserNew user) {
        return given(rspec)
                .basePath(USER)
                .body(user)
                .log().all()
              .when()
                .post()
              .then()
                .log().all();
    }
}
