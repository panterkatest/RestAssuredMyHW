package services;

import dto.Pet;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class PetApi {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String PET_PATH = "/pet";
    private RequestSpecification reqspec;
    private ResponseSpecification respec;

    public PetApi(){
        reqspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);

        respec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreatePet.json"))
                .build();
    }



//    //Рабочий варинат, но без ResponseSpecification
//    public ValidatableResponse createPet (Pet pet){
//        return given(reqspec)
//                .basePath(PET_PATH)
//                .body(pet)
//                .log().all()
//        .when()
//                .post()
//        .then()
//                .log().all();
//    }


    public ValidatableResponse createPet (Pet pet){
        return given(reqspec)
                .basePath(PET_PATH)
                .body(pet)
                .log().all()
           .when()
                .post()
           .then()
                .spec(respec)
                .log().all();
    }


    public ValidatableResponse updatePet (Pet pet, long petId){
        return given(reqspec)
                .basePath( PET_PATH + "/" + petId)
                .body(pet)
                .log().all()
          .when()
                .post()
          .then()
                .log().all();
    }
}
