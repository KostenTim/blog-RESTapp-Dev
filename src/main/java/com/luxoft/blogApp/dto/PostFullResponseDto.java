package com.luxoft.blogApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luxoft.blogApp.entity.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFullResponseDto {

    private Long id;

    private String title;

    private String content;

    private boolean star;

    private List<Comment> comments;

}
