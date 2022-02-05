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

    private final CommentRepository commentRepository;


    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment update(Comment comment) {
        if(comment.getId() == null){
            return null;
        }
        Comment existingComment = getById(comment.getId());
        if (existingComment != null) {
            return commentRepository.save(existingComment);
        } else {
            return null;
        }
    }

    @Override
    public List<Comment> getAllByPostId(Long postId) {
        return commentRepository.findByPost_Id(postId);
    }

    @Override
    public Comment getByIdAndPostId(Long id, Long postId) {
        return commentRepository.findByIdAndPost_Id(id, postId).orElse(null);
    }
}
