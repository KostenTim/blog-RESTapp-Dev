package com.luxoft.blogApp.service;

import com.luxoft.blogApp.entity.Comment;
import com.luxoft.blogApp.entity.Post;
import com.luxoft.blogApp.repository.CommentRepository;
import com.luxoft.blogApp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getAllByPostId(Long postId) {
        return commentRepository.findByPost_Id(postId);
    }
}
