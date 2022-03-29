package com.dhruv.blogapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Blog blog;
}
