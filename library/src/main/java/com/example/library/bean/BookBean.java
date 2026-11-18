package com.example.library.bean;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//@Range checks whether a numeric value falls within a specified range.
//@Size checks length or size
public record BookBean (Integer id,
    @NotBlank (message = "{NotBlank.BookBean.title}") String title, 
    @NotEmpty String author,

    @NotNull (message = "{NotNull.BookBean.Price}")
    @Range (min=100,max=2000) 
    Double price
){

}
