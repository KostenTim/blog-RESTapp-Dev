package com.luxoft.blogApp.service;

import com.luxoft.blogApp.entity.Post;
import com.luxoft.blogApp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultService implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
            return postRepository.save(post);
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }


    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }


    @Override
    public Post update(Post post) {
        if(post.getId() == null){
            return null;
        }
        Post existingPost = postRepository.findById(post.getId()).orElse(null);
        if (existingPost != null) {
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    @Override
    public List<Post> findAllByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findAllWithSort(String sortCriteria) {
        try{
            return postRepository.findAll(Sort.by(sortCriteria));
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public List<Post> returnMarkedByStar() {
        return postRepository.findAllByStarIsTrue();
    }

    @Override
    public Post markedByStar(Long id) {
       Post post =  getById(id);
       if(post!=null){
           post.setStar(true);
           return postRepository.save(post);
       }else{
           return null;
       }
    }

    @Override
    public Post unmarkedByStar(Long id) {
        Post post =  getById(id);
        if(post!=null){
            post.setStar(false);
            return postRepository.save(post);
        }else{
            return null;
        }
    }
}
