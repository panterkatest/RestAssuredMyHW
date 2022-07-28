package PetTests;

import dto.Pet;
import dto.PetCategory;
import dto.PetOut;
import dto.PetTagsItem;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.PetApi;

import java.util.Collections;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreatePet {
    private long id = 001;
    private String catName = "Murzik";
    private String catStatus1 = "New";

    private String catStatus2 = "Adopted";

    private String catCategory = "Cats";

    private int idCategory = 01;

    private int idTag = 1;

    private String nameTag = "Outbred";

    private String photoUrl1 = "https://i.etsystatic.com/33728303/r/il/239341/3579213608/il_794xN.3579213608_re6w.jpg";

    //Создание сущности pet и проверка правильно ли заполнены все поля, a так же наличие всех обяхательных полей у сущности pet
    //ПРоверяю, что сгенерировался id для сущности pet
    @Test
    public void createPet(){
        Pet pet = Pet.builder()
                .name(catName)
                .status(catStatus1)
                .category(PetCategory.builder().id(idCategory).name(catCategory).build())
                .tags(Collections.singletonList(PetTagsItem.builder().id(idTag).name(nameTag).build()))
                .photoUrls(Collections.singletonList(photoUrl1))
                .build();

        PetApi petApi = new PetApi();
        petApi.createPet(pet)
                .body("id", notNullValue())
                .body("name", equalTo(catName))
                .body("status", equalTo(catStatus1))
                .body("category.id", equalTo(idCategory))
                .body("category.name", equalTo(catCategory))
                .body("tags[0].id", equalTo(idTag))
                .body("tags[0].name", equalTo(nameTag))
                .body("photoUrls[0]", equalTo(photoUrl1));

        //Дополнительно второй способ
        ValidatableResponse response = petApi.createPet(pet);
        PetOut petOut = response.extract().body().as(PetOut.class);

        Assertions.assertEquals(catStatus1, petOut.getStatus());

        /*
        Вопрос:
        Как написать правильно следующую проверку? У меня не получилось, но хотелось бы попробовать.
        Спасибо
        List<PetTagsItem> tagsList = response.extract().jsonPath().getList("tags");
        Assertions.assertEquals(tagsList.get(0),nameTag);
        Assertions.assertTrue(tagsList.contains(nameTag));
         */

    }

    //Негативный тест: можно ли создать сущность не заполнив обязательные поля имя и статус
    @Test
    //Вопрос:
    //не понятно как создать такой тест, чтобы он проверял, что приходит ошибка с определённым текстом, если не заполнили какие-то поля.
    //У меня постоянно падает на ответе, при этом проверить его не получается.
    //Как поправить мой тест, чтобы работало, как задумано?
    //Для него надо закомментить ValidatableResponse createPet и раскомментить тот, что без ResponseSpecification на странице PetApi
    public void createInvalidPet(){
        PetApi petApi = new PetApi();
        String responseText;
        Pet pet = Pet.builder()
                .category(PetCategory.builder().id(idCategory).name(catCategory).build())
                .tags(Collections.singletonList(PetTagsItem.builder().id(idTag).name(nameTag).build()))
                .photoUrls(Collections.singletonList(photoUrl1))
                .build();

        ValidatableResponse response = petApi.createPet(pet);
        try {
            responseText = response.spec(new ResponseSpecBuilder()
                    .expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreatePet.json")).build()).extract().response().toString();
            System.out.println(responseText);
        }catch (Exception e){
            System.out.println(e);
        }
    }



    //СОздаю сущность pet
    //Получаю её id и обновляю статус.
    //ПРоверяю, что статус обновился
    @Test
    public void updatePet() {
        Pet pet = Pet.builder()
                .name(catName)
                .status(catStatus1)
                .category(PetCategory.builder().id(idCategory).name(catCategory).build())
                .tags(Collections.singletonList(PetTagsItem.builder().id(idTag).name(nameTag).build()))
                .photoUrls(Collections.singletonList(photoUrl1))
                .build();

        PetApi petApi = new PetApi();
        Long petId;

        ValidatableResponse response = petApi.createPet(pet);
        petId = response.extract().body().as(PetOut.class).getId();

        pet = Pet.builder()
                .id(petId)
                .name(catName)
                .status(catStatus2)
                .category(PetCategory.builder().id(idCategory).name(catCategory).build())
                .tags(Collections.singletonList(PetTagsItem.builder().id(idTag).name(nameTag).build()))
                .photoUrls(Collections.singletonList(photoUrl1))
                .build();

        //Проверяет обновился ли статус и падает, т.к. этот функционал реально не работает в аппе
        petApi.updatePet(pet,petId)
                .body("status", equalTo(catStatus2));

    }
}
