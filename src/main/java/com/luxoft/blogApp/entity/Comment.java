package com.luxoft.blogApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
    @Column
    private Date creationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;
}
