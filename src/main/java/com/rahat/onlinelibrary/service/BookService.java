package com.rahat.onlinelibrary.service;

import com.rahat.onlinelibrary.entity.BookEntity;
import com.rahat.onlinelibrary.model.BookRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    ResponseEntity<Object> createBook(BookRequestModel bookRequestModel);


    List<BookEntity> getAllBooks();

    Optional<BookEntity> getBook(Long bookId);

    List<BookEntity> getBookByAuthorName(String authorName);

    BookEntity updateBook(Long bookId,BookRequestModel bookRequestModel);

    void deleteBook(Long bookId);

    BookEntity findByAuthorNameAndBookName(String authorName, String bookName);
}
