package com.yaz.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Comment extends Auditable{
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String body;

    //link
    @ManyToOne
    @NonNull
    private Link link;

    public Comment(@NonNull String body, @NonNull Link link) {
        this.body = body;
        this.link = link;
    }
}
