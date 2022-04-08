package com.dhruv.blogapp.repositories;

import com.dhruv.blogapp.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BlogRepo extends JpaRepository<Blog, Long> {

}
