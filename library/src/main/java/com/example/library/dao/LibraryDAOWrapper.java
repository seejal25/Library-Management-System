package com.example.library.dao;
import com.example.library.bean.BookBean;
import com.example.library.entity.Book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository 
public class LibraryDAOWrapper {

//Autowiring EmployeeDAO to use its methods
	//Spring will provide the implementation class of EmployeeDAO interface
    // @Autowired 
    // LibraryDAO libraryDAO;

    private final LibraryDAO libraryDAO;

    public LibraryDAOWrapper(LibraryDAO libraryDAO){
        this.libraryDAO=libraryDAO;
    }

    public Collection<BookBean> getAllBook(){
        //if jpa used , findAll returns List<T>
        Iterable<Book> bookEntity = libraryDAO.findAll();
        List<BookBean> bookBean=new ArrayList<>();
        for(Book bookEntities:bookEntity){
            bookBean.add(toBookBean(bookEntities));
        }
        return bookBean;

    }

    public BookBean getById(int id){
        //simpler 
        //return employeeDAO.findById(employeeId)
        //    .map(this::toEmployeeBean)
          //  .orElse(null);

        Optional<Book> bookEntityResult=libraryDAO.findById(id);
        BookBean book=null;
        //employeeEntityResult is not an EmployeeEntity object.
        //  It is an Optional<EmployeeEntity> object.
        //isPresent and get are optional class methods

        if(bookEntityResult.isPresent()){
            Book bookEntity=bookEntityResult.get();
            book=toBookBean(bookEntity);
        }
        return book;


    }

    public int addBook(BookBean book){
        Book bookEntity=toBookEntity(book);
        
        Book savedBook=libraryDAO.save(bookEntity);
        return savedBook.getId();
    }
    
    public BookBean updateBookDetails(BookBean bookBean){
        Optional<Book> bookEntityResult=libraryDAO.findById(bookBean.id());
        BookBean updatedBookBean=null;
        if(bookEntityResult.isPresent()){
            Book bookEntity=bookEntityResult.get();
            bookEntity.setAuthor(bookBean.author());
            bookEntity.setId(bookBean.id());
            bookEntity.setTitle(bookBean.title());
            bookEntity.setPrice(bookBean.price());
            Book updatedBookEntity=libraryDAO.save(bookEntity);
            updatedBookBean=toBookBean(updatedBookEntity);
        }
        return updatedBookBean;

    }

    public BookBean deleteBookByID(int id){
        Optional<Book> bookEntityResult=libraryDAO.findById(id);
        BookBean bookBean=null;
        if(bookEntityResult.isPresent()){
            Book bookEntity=bookEntityResult.get();
            libraryDAO.delete(bookEntity);
            bookBean=toBookBean(bookEntity);
        }
        return bookBean;
    
    }

    public Collection<BookBean> findBookByAuthor(String author){
        List<Book> books=libraryDAO.findBooksByAuthor(author);
        List<BookBean> bookBean=new ArrayList<>();
        for(Book bookEntity:books){
            bookBean.add(toBookBean(bookEntity));
        }
        return bookBean;
    }

    public Collection<BookBean> getBooksByPrice(int price){
        List<Book> bookEntity=libraryDAO.getBooksbyPrice(price);
        List<BookBean> bookBean=new ArrayList<>();
        for(Book entity:bookEntity){
            bookBean.add(toBookBean(entity));
        }
        return bookBean;
    }
    public int updateBooksPrice(int amt,String author){
        return libraryDAO.updatePrice(amt, author);
    }
    private BookBean toBookBean(Book bookEntity ){
        return new BookBean(
            bookEntity.getId(),
            bookEntity.getTitle(),
            bookEntity.getAuthor(),
            bookEntity.getPrice()
        );
        // BookBean bookBean = new BookBean();
        // BeanUtils.copyProperties(bookEntity, bookBean); 
        // return bookBean;
    }

    private Book toBookEntity(BookBean bookBean){
        Book bookEntity = new Book();
        bookEntity.setAuthor(bookBean.author());
        bookEntity.setId(bookBean.id());
        bookEntity.setPrice(bookBean.price());
        bookEntity.setTitle(bookBean.title());
        return bookEntity;
    }

    
     
}
