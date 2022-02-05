package com.luxoft.blogApp.repository;

import com.luxoft.blogApp.entity.Comment;
import com.luxoft.blogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_Id(Long postId);

}