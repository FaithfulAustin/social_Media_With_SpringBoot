package com.faithful.social_Media_App.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String content;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User    user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Tags_Posts",
            joinColumns=@JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "tag_id", referencedColumnName = "id")
    )

    private Set<Tag> tags;

}
