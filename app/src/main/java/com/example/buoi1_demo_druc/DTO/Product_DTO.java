package com.example.buoi1_demo_druc.DTO;

public class Product_DTO {
    private int id,pricae,idcat;
    private String name;

    public Product_DTO(int id, String name, int gia, int idcat) {
        this.id = id;
        this.name = name;
        this.pricae = gia;
        this.idcat = idcat;
    }

    public Product_DTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricae() {
        return pricae;
    }

    public void setPricae(int pricae) {
        this.pricae = pricae;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
