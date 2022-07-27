package com.yaz.springit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private short direction;

    @ManyToOne
    @NonNull
    private Link link;



    //user
    //link


}
