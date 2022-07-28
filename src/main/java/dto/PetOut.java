package dto;

import java.util.List;

public class PetOut{
    private List<String> photoUrls;
    private String name;
    private long id;
    private PetCategoryOut category;
    private List<PetTagsItemOut> tags;
    private String status;

    public List<String> getPhotoUrls(){
        return photoUrls;
    }

    public String getName(){
        return name;
    }

    public long getId(){
        return id;
    }

    public PetCategoryOut getCategory(){
        return category;
    }

    public List<PetTagsItemOut> getTags(){
        return tags;
    }

    public String getStatus(){
        return status;
    }
}