package com.yaz.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
@NoArgsConstructor
@Data
public class commet {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String body;

    //link

}
