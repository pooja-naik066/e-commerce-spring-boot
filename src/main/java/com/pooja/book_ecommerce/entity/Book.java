package com.pooja.book_ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Embeddable
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String description;

    private String genre;

    @NotNull
    @DecimalMin(value = "0.00")
    private Double price;

    @NotNull
    @Min(value = 0)
    private Integer quantity;

}
