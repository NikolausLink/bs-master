package com.example.demo.repository;

import com.example.demo.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("SELECT u FROM BookEntity u WHERE u.isbn LIKE :param1 OR u.title LIKE :param2")
    List<BookEntity> findAllByTitleOrIsbn(@Param("param1") String param1, @Param("param2") String param2);
}
