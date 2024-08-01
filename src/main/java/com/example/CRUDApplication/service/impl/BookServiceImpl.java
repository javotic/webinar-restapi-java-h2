package com.example.CRUDApplication.service.impl;

import com.example.CRUDApplication.model.BookModel;
import com.example.CRUDApplication.repo.BookRepository;
import com.example.CRUDApplication.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookModel> getAllBooks() {
        List<BookModel> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);
        return bookList;

    }

    @Override
    public Optional<BookModel> getBookById(Long id) {
        Optional<BookModel> bookObj = bookRepository.findById(id);
        return bookObj;
    }

    @Override
    public BookModel addBook(BookModel book) {
        BookModel bookObj = bookRepository.save(book);
        return bookObj;
    }

    @Override
    public BookModel updateBook(Long id, BookModel book) {
        Optional<BookModel> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            BookModel updatedBookData = bookData.get();
            updatedBookData.setTitle(book.getTitle());
            updatedBookData.setAuthor(book.getAuthor());

            BookModel bookObj = bookRepository.save(updatedBookData);
            return bookObj;
        }

        return null;
    }

    @Override
    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllBooks() {
        bookRepository.deleteAll();
        return true;
    }

}
