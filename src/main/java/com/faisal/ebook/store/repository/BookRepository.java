package com.faisal.ebook.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faisal.ebook.store.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

}
