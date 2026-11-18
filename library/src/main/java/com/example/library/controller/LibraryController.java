package com.example.library.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.library.bean.BookBean;

import com.example.library.service.LibraryServiceImpl;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("api/books")
public class LibraryController {
    private LibraryServiceImpl libraryServiceImpl;

    public LibraryController(LibraryServiceImpl libraryServiceImpl){
        this.libraryServiceImpl=libraryServiceImpl;
    }

    @GetMapping(value="/getDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BookBean>> getBookDetails(){
        Collection<BookBean> listBook = libraryServiceImpl.getAllBook();
        //response entity has a structured model body status, 
        //  new ResponseEntity<>(BODY, STATUS);
        return new ResponseEntity<Collection<BookBean>>(listBook,HttpStatus.OK);
    }

    @GetMapping (value="/getById/{id}")
    //? bcuz bookbean and string is used , if u dont want custom msg do normal one
    public ResponseEntity<?> getBookById(@PathVariable ("id") int bookId){
        BookBean book=libraryServiceImpl.getBookById(bookId);
        if(book!=null){
            return new ResponseEntity<BookBean>(book,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Your ID does not exist",HttpStatus.NOT_FOUND);
            
        } 
    }

    //How does Spring convert that incoming JSON into an EmployeeBean object?
    // That's exactly what @RequestBody does.
    @PostMapping(value="/addBook")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookBean book,Errors err){
        if(err.hasErrors()){
            return new ResponseEntity<String>(err.getAllErrors()+" ",HttpStatus.BAD_REQUEST);
        }
        int id=libraryServiceImpl.addBook(book);
        return new ResponseEntity<String>("Book has added successfully with "+id,HttpStatus.CREATED);
    }

    
    
    @PutMapping (value="/updateBook")
   // @RequestBody can be used with record only
    public ResponseEntity<BookBean> updateBook(@Valid @RequestBody BookBean book){
        if(libraryServiceImpl.getBookById(book.id())==null){
            BookBean book2=null;
            return new ResponseEntity<BookBean>(book2,HttpStatus.NOT_FOUND);
        }
        BookBean updatedBook=libraryServiceImpl.updateBook(book);
        return new ResponseEntity<BookBean>(updatedBook,HttpStatus.OK);
    }

    @DeleteMapping (value = "/deleteBook/{id}")
    public ResponseEntity<BookBean> deleteBookById(@PathVariable ("id") int bookId){
        BookBean bookbean=libraryServiceImpl.getBookById(bookId);
        if(bookbean==null){
            return new ResponseEntity<BookBean>(bookbean,HttpStatus.NOT_FOUND);
        }
        BookBean deletedBook=libraryServiceImpl.deleteBook(bookId);
        return new ResponseEntity<>(deletedBook,HttpStatus.OK);
    }

    @GetMapping (value="/author/{author}")
    public ResponseEntity<Collection<BookBean>> getByAuthor(@PathVariable ("author") String author){
        return new ResponseEntity<>(libraryServiceImpl.findByAuthor(author),HttpStatus.OK);
    }
    @GetMapping (value="/price/{price}")
    public ResponseEntity<Collection<BookBean>> getByPrice(@PathVariable ("price") int price){
        return new ResponseEntity<>(libraryServiceImpl.getByPrice(price),HttpStatus.OK);
    }

    @PutMapping (value="/increasePrice")
    public ResponseEntity<String> increasePrice(
        @RequestParam int amt,
        @RequestParam String author){
        
            int count=libraryServiceImpl.updateBooksPrice(amt, author);
            return new ResponseEntity<>(count+" books updated",HttpStatus.OK);
    }

    


}
