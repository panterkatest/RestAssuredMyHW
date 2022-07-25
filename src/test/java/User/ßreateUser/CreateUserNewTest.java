package User.сreateUser;

import dto.Book;
import dto.UserNew;
import dto.UserOut;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApiNew;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserNewTest {
    
    @Test
    public void checkUser() {
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("wewe")
                .id(122L)
                .firstName("firstName")
                .lastName("lastName")
                .password("pass")
                .username("userName")
                .phone("phone")
                .build();

        UserApiNew userApi = new UserApiNew();

        userApi.createUser(user)
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo("122"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
               // .body("", )

        //List<Book> books =  response.extract().jsonPath().getList("store.book");

     /*   ValidatableResponse response = userApi.createUser(user);
        UserOut  userOut = response.extract().body().as(UserOut.class);

        Assertions.assertEquals("122", userOut.getMessage());*/
    }
}
