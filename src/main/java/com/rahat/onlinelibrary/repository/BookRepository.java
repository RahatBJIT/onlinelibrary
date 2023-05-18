package com.rahat.onlinelibrary.repository;

import com.rahat.onlinelibrary.entity.BookEntity;
import com.rahat.onlinelibrary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity,Long> {
    public  List<BookEntity> findByAuthorNameContaining(String authorName);
    public BookEntity findByAuthorNameAndBookName(String authorName, String bookName);
}
