package com.example.library_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_management.model.Book;
import com.example.library_management.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails){
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setPublishedDate(bookDetails.getPublishedDate());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}
