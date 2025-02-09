package com.assignment.onlineShop.service.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
public class AddressDto {
    private Long id;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String number;
    @NotNull
    @NotEmpty
    @Length(min = 5, max = 5)
    private String postalCode;
}
