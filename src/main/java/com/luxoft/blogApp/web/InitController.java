package com.luxoft.blogApp.web;

import com.luxoft.blogApp.entity.Comment;
import com.luxoft.blogApp.entity.Post;
import com.luxoft.blogApp.service.CommentServiceImpl;
import com.luxoft.blogApp.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/api/init")
@RequiredArgsConstructor
public class InitController {

    private final DefaultService defaultService;
    private final CommentServiceImpl commentService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/post")
    public String postInit() {
        defaultService.save(
                Post.builder()
                        .id(1L)
                        .title("title1")
                        .content("content1")
                        .star(true)
                        .build()
        );
        defaultService.save(
                Post.builder()
                        .id(3L)
                        .title("title3")
                        .content("content3")
                        .star(false)
                        .build()
        );
        defaultService.save(
                Post.builder()
                        .id(2L)
                        .title("title2")
                        .content("content2")
                        .star(true)
                        .build()
        );
        return "ok";
    }


    @GetMapping("/comment")
    public String commentInit() {
        postInit();

        commentService.save(Comment.builder()
                        .id(0L)
                        .text("text1")
                        .creationDate(new Date())
                        .post(defaultService.getAll().get(0))
                .build());

        commentService.save(Comment.builder()
                .id(0L)
                .text("text2")
                .creationDate(new Date())
                .post(defaultService.getAll().get(0))
                .build());

        commentService.save(Comment.builder()
                .id(0L)
                .text("text2")
                .creationDate(new Date())
                .post(defaultService.getAll().get(1))
                .build());
        return "ok";
    }


}
