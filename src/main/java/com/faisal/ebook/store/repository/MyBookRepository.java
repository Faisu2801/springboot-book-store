package com.faisal.ebook.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faisal.ebook.store.entity.MyBookList;

public interface MyBookRepository extends  JpaRepository<MyBookList,Integer>{

}
