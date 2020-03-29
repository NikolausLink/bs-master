package com.example.demo.repository;

import com.example.demo.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.saveAndFlush(bookEntity);
    }

    public BookEntity getBookById(int id) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookEntity> findAllByTitle(String searchWord) {
        return bookRepository.findAllByTitleOrIsbn('%' + searchWord + '%', '%' + searchWord + '%');
    }
}
