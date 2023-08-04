package com.example.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code", unique = true, length = 10, nullable = false)
    @Length(min = 10)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_eur", nullable = false, scale = 2)
    @PositiveOrZero
    private double priceEur;

    @Column(name = "price_usd", nullable = false, scale = 2)
    @PositiveOrZero
    private double priceUsd;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available")
    private boolean isAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
