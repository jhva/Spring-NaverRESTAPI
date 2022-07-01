package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs <T>{

    //옵셔널하게 리턴 ,
    Optional<T> findById (int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findAll();
}
