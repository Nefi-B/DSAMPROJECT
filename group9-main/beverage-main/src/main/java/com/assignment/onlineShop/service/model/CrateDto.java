package com.assignment.onlineShop.service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrateDto {
    private Long id;
    @NotBlank(message = "Name is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name can only contain letters and digits")
    private String name;
    @URL
    private String cratePic;
    @Positive(message = "Number Of Bottles must be a positive number.")
    private Integer noOfBottles;
    @Positive(message = "price must be a positive number.")
    private Double price;
    @Min(value = 0, message = "Crates in stock must be greater than or equal to 0")
    private Integer cratesInStock;
    private List<BottleDto> bottles;
}
