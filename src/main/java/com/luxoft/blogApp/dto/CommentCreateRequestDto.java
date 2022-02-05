package com.luxoft.blogApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luxoft.blogApp.entity.Comment;
import com.luxoft.blogApp.entity.Post;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {

    private String text;

    private Long postId;

}
