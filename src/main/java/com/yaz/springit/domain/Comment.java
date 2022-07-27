package com.yaz.springit.domain;

import com.yaz.springit.service.BeanUtil;
import lombok.*;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
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

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
