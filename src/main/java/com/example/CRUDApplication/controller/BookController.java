package com.example.CRUDApplication.controller;

import com.example.CRUDApplication.model.BookModel;
import com.example.CRUDApplication.repo.BookRepository;
import com.example.CRUDApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        try {
            List<BookModel> bookList = bookService.getAllBooks();
            return bookList.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        Optional<BookModel> bookObj = bookService.getBookById(id);
        return bookObj.isPresent()
                ? new ResponseEntity<>(bookObj.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {
        try {
            BookModel bookObj = bookService.addBook(book);
            return new ResponseEntity<>(bookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateBook/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel book) {
        try {
            BookModel bookObj = bookService.updateBook(id, book);
            return bookObj == null 
                    ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
                    : new ResponseEntity<>(bookObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        try {
            return bookService.deleteBook(id) 
                    ? new ResponseEntity<>(HttpStatus.OK) 
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllBooks")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            return bookService.deleteAllBooks() 
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
