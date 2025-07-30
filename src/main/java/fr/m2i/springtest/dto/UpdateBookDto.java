package fr.m2i.springtest.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateBookDto {
        @NotBlank
        private String title;


    public UpdateBookDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
