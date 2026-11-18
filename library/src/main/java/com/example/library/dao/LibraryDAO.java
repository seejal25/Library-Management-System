package com.example.library.dao;
import com.example.library.entity.Book;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LibraryDAO extends JpaRepository<Book,Integer>{

    //to Find Books By Author
    @Query ("SELECT b FROM Book b WHERE b.author= ?1") //positional parameter
    List<Book> findBooksByAuthor(String author);

    @Query ("SELECT b FROM Book b WHERE b.price > :p")
    List<Book> getBooksbyPrice(@Param ("p")int price);

    @Modifying //@Modifying – tells Spring Data JPA this query modifies data.
    @Transactional //@Transactional – executes it within a transaction.
    @Query ("UPDATE Book b SET b.price=b.price+ :amt WHERE b.author= :author")
    int updatePrice(@Param ("amt") int amount,@Param ("author") String author);

    
} 
