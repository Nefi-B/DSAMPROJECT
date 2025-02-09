package com.assignment.onlineShop.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CRATE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Crate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cratePic;
    private Integer noOfBottles;
    private Double price;
    private Integer cratesInStock;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "crate_id")
    private List<Bottle> bottles;
}
