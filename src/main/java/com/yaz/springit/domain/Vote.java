package com.yaz.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Vote {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private int voting;



    //user
    //link


}
