package com.citronix.dto.req;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


// @Builder
public class FarmDTO {

    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String address;

    @DecimalMin(value = "0.1", message = "Area must be greater than 0")
    @NotNull(message = "Surface is required")
    private double surface;

    public FarmDTO(){}

    public FarmDTO(long id, String name, String address, double surface) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.surface = surface;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Address cannot be blank") @Size(max = 200, message = "Address must not exceed 200 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address cannot be blank") @Size(max = 200, message = "Address must not exceed 200 characters") String address) {
        this.address = address;
    }

    @Min(message = "Surface must be greater than 0", value = 0)
    @NotNull(message = "Surface is required")
    public double getSurface() {
        return surface;
    }

    public void setSurface(@Min(message = "Surface must be greater than 0", value = 0) @NotNull(message = "Surface is required") double surface) {
        this.surface = surface;
    }
}
