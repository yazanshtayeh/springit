package com.yaz.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String body;

    //link
    @ManyToOne
    private Link link;
}
