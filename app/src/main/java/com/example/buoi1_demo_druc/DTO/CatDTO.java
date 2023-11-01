package com.example.buoi1_demo_druc.DTO;

public class CatDTO {
    private int id;
    private String name;

    public CatDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CatDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
