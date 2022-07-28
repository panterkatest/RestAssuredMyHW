package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Pet{
    private List<String> photoUrls;
    private String name;
    private long id;
    private PetCategory category;
    private List<PetTagsItem> tags;
    private String status;

}