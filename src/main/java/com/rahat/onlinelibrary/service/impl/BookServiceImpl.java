package com.rahat.onlinelibrary.service.impl;

import com.rahat.onlinelibrary.entity.BookEntity;
import com.rahat.onlinelibrary.entity.Role;
import com.rahat.onlinelibrary.entity.UserEntity;
import com.rahat.onlinelibrary.model.AuthenticationResponse;
import com.rahat.onlinelibrary.model.BookRequestModel;
import com.rahat.onlinelibrary.repository.BookRepository;
import com.rahat.onlinelibrary.repository.UserRepository;
import com.rahat.onlinelibrary.service.BookService;
import com.rahat.onlinelibrary.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;




    @Override
    public ResponseEntity<Object> createBook(BookRequestModel bookRequestModel) {
        BookEntity bookEntity = BookEntity.builder()
                .authorName(bookRequestModel.getAuthorName())
                .bookName(bookRequestModel.getBookName())
                .year(bookRequestModel.getYear())
                .subject(bookRequestModel.getSubject())
                .build();
        BookEntity savedBook= bookRepository.save(bookEntity);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);

    }

    @Override
    public List<BookEntity> getAllBooks() {
       List <BookEntity> books= bookRepository.findAll();
        return books ;
    }

    @Override
    public Optional<BookEntity> getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public List<BookEntity> getBookByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContaining(authorName);
    }

    @Override
    public BookEntity updateBook(Long bookId ,BookRequestModel bookRequestModel) {
        Optional<BookEntity> userExistedAlready = bookRepository.findById(bookId);
        if (userExistedAlready.isEmpty()){
            throw new UsernameNotFoundException("This Book Id is Invalid");
            /////
        }
        else {
            BookEntity bookEntity = BookEntity.builder()
                    .id(bookId)
                    .authorName(bookRequestModel.getAuthorName())
                    .bookName(bookRequestModel.getBookName())
                    .year(bookRequestModel.getYear())
                    .subject(bookRequestModel.getSubject())
                    .build();

            BookEntity savedValue= bookRepository.save(bookEntity);

            return savedValue;

        }
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookEntity findByAuthorNameAndBookName(String authorName, String bookName) {
        return bookRepository.findByAuthorNameAndBookName( authorName,  bookName);
    }
}
