package dto;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

@Data
@Builder
@AllArgsConstructor
public class PetTagsItem{
    private String name;
    private int id;
}
