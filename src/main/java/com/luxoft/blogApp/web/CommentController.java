package com.luxoft.blogApp.web;

import com.luxoft.blogApp.dto.CommentCreateRequestDto;
import com.luxoft.blogApp.entity.Comment;
import com.luxoft.blogApp.entity.Post;
import com.luxoft.blogApp.service.CommentServiceImpl;
import com.luxoft.blogApp.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final DefaultService defaultService;
    private final CommentServiceImpl commentServiceImpl;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping
    ResponseEntity<Comment> save(@RequestBody CommentCreateRequestDto dto) {
        Post post = defaultService.getById(dto.getPostId());
        if(post == null){
            return  new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
        }else{
           Comment comment =  commentServiceImpl.save(Comment.builder()
                    .text(dto.getText())
                    .creationDate(new Date())
                    .post(post)
                    .build());
            logger.info("obtain request to save new comment {} ",comment );
            return new ResponseEntity<Comment>(comment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Comment> delete(@PathVariable Long id) {
        Comment comment = commentServiceImpl.getById(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            commentServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Comment> update(@RequestParam String text, @PathVariable Long id) {
        Comment comment = commentServiceImpl.getById(id);
        comment.setText(text);
        Comment updatedComment = commentServiceImpl.update(comment);
        if (updatedComment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        }
    }

    @GetMapping()
    ResponseEntity<List<Comment>> getAll() {
            return new ResponseEntity<List<Comment>>(commentServiceImpl.getAll(), HttpStatus.OK);
        }

    @GetMapping("/{id}")
    ResponseEntity<Comment> update(@PathVariable Long id) {
        Comment comment = commentServiceImpl.getById(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }



}
