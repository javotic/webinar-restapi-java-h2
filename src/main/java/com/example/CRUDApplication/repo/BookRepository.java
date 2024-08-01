package com.example.CRUDApplication.repo;

import com.example.CRUDApplication.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {

}
