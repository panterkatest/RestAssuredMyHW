package dto;

import java.util.List;

public class Pet {
    private List<String> photoUrls;
    private String name;
    private int id;
    private PetCategory category;
    private List<PetTagsItem> tags;
    private String status;
}