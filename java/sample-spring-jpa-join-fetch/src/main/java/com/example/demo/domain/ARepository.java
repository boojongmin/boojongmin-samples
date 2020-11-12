package com.example.demo.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ARepository extends PagingAndSortingRepository<AEntity, Long> {

    @Query("select DISTINCT a from AEntity a JOIN FETCH a.bList b WHERE b.del = false")
    List<AEntity>  hello();

}

