package com.example.library.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.library.bean.BookBean;
import com.example.library.dao.LibraryDAOWrapper;

@Service 
public class LibraryServiceImpl {

    private LibraryDAOWrapper libraryDAOWrapper;

    public LibraryServiceImpl(LibraryDAOWrapper libraryDAOWrapper){
        this.libraryDAOWrapper=libraryDAOWrapper;
    }

    public Collection<BookBean> getAllBook(){
       return libraryDAOWrapper.getAllBook();
    }

    public BookBean getBookById(int id){
        return libraryDAOWrapper.getById(id);
    }

    public int addBook(BookBean book){
        return libraryDAOWrapper.addBook(book);
    }

    public BookBean updateBook(BookBean book){
        return libraryDAOWrapper.updateBookDetails(book);
    }

    public BookBean deleteBook(int id){
        return libraryDAOWrapper.deleteBookByID(id);
    }

    public Collection<BookBean> findByAuthor(String author){
        return libraryDAOWrapper.findBookByAuthor(author);
    }

    public Collection<BookBean> getByPrice(int price){
        return libraryDAOWrapper.getBooksByPrice(price);
    }

    public int updateBooksPrice(int amt,String author){
        return libraryDAOWrapper.updateBooksPrice(amt, author);
    }

}
