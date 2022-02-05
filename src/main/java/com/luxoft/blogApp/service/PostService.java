package com.luxoft.blogApp.service;

import com.luxoft.blogApp.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();

    Post save(Post post);

    Post getById(Long id);

    void delete(Long id);

    Post update(Post post);

    List<Post> findAllByTitle(String sortCriteria);

    List<Post> returnMarkedByStar();

    Post markedByStar(Long id);

    Post unmarkedByStar(Long id);


}

