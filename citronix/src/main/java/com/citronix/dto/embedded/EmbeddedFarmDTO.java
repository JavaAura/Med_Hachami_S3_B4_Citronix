package com.citronix.dto.embedded;

public class EmbeddedFarmDTO {
    private Long id;
    private String name;
    private String adddress;
    private double surface;

    public EmbeddedFarmDTO() {
    }

    public EmbeddedFarmDTO(Long id, String name, String adddress, double surface) {
        this.id = id;
        this.name = name;
        this.adddress = adddress;
        this.surface = surface;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }
}
