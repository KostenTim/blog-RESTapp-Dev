package com.luxoft.blogApp.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDto {


    private Long id;

    private String text;

}
