package com.example.CRUDApplication.service;

import com.example.CRUDApplication.model.BookModel;
import java.util.List;
import java.util.Optional;

public interface BookService {
    
    List<BookModel> getAllBooks();
    
    Optional<BookModel> getBookById(Long id);
    
    BookModel addBook(BookModel book);
    
    BookModel updateBook(Long id, BookModel book);
    
    boolean deleteBook(Long id);
    
    boolean deleteAllBooks();
}
