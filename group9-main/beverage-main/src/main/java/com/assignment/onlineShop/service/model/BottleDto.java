package com.assignment.onlineShop.service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottleDto {
    private Long id;
    @NotBlank(message = "Name is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name can only contain letters and digits")
    private String name;
    @URL
    private String bottlePic;
    @Positive(message = "volume must be a positive number.")
    private Double volume;
    private Boolean isAlcoholic;
    private Double volumePercent;
    @Positive(message = "Price must be a positive number.")
    private Double price;
    @NotBlank(message = "Supplier is required.")
    private String supplier;
    @Min(value = 0, message = "In stock must be greater than or equal to 0")
    private Integer inStock;
    @Positive(message = "quantity must be a positive number.")
    private Integer quantity;
}
