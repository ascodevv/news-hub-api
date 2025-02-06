package com.ascodev.newshubbackend.repository;

import com.ascodev.newshubbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
