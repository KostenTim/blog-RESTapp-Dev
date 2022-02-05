package com.luxoft.blogApp.repository;

import com.luxoft.blogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findAllByStarIsTrue();

    List<Post> findAllByStar(boolean star);

}