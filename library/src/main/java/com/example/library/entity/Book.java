package com.example.library.entity;

import jakarta.persistence.*;

@Entity 
public class Book {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;
    private Double price;

    //no arg const
    public Book(){
        super();
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer book_id){
        this.id=book_id;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }

    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price=price;
    }
}
