package net.codejava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VocabDto {
    private String word;
    private String definition;
    private String example;
    private String type;
    private String pronounce;
    private String imageURL;
    private Long userId;


    public VocabDto(String word, String definition, String example, String type, String pronounce, String imageURL, Long userId) {
        this.word = word;
        this.definition = definition;
        this.example = example;
        this.type = type;
        this.pronounce = pronounce;
        this.imageURL = imageURL;
        this.userId = userId;
    }

    public VocabDto(String word, String definition, String example, String type, Long userId) {
        this.word = word;
        this.definition = definition;
        this.example = example;
        this.type = type;
        this.userId = userId;
    }
}
