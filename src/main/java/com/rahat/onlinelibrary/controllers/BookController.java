package com.rahat.onlinelibrary.controllers;

import com.rahat.onlinelibrary.entity.BookEntity;
import com.rahat.onlinelibrary.model.BookRequestModel;
import com.rahat.onlinelibrary.model.UserRequestModel;
import com.rahat.onlinelibrary.service.BookService;
import com.rahat.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
//        return userService.register(requestModel);
//    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBook(@RequestBody BookRequestModel bookRequestModel){
        return bookService.createBook(bookRequestModel);
    }

    @GetMapping("/all")
    public List<BookEntity> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/id/{bookId}")
    public Optional<BookEntity> getBook(@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }

    @GetMapping("/author/{authorName}")
    public List<BookEntity> getBookByAuthorName(@PathVariable String authorName){
        return bookService.getBookByAuthorName(authorName);
    }
    @PutMapping("/update/{bookId}")
    public BookEntity updateBook(@PathVariable Long bookId, @RequestBody BookRequestModel bookRequestModel ){
        return bookService.updateBook(bookId, bookRequestModel);
    }
    @GetMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
         bookService.deleteBook(bookId);
    }
    @GetMapping("/{authorName}/{bookName}")
    public BookEntity findByAuthorNameAndBookName(@PathVariable String authorName, @PathVariable String bookName){
      return   bookService.findByAuthorNameAndBookName(authorName,bookName);
    }



}
