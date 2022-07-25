package com.yaz.springit.domain;

import com.yaz.springit.service.BeanUtil;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Link extends Auditable{
    @Id
    @GeneratedValue
    private long id;
    @NonNull @NotEmpty(message = "please enter a title")
    private String title;
    @NonNull @NotEmpty(message = "please enter a URL")
    @URL(message = "please enter a valid URL")
    private String url;


    //comments
    @OneToMany(mappedBy = "link")
    private List<Comment> comments =new ArrayList<>();

//    public Link(@NonNull String title, @NonNull String url) {
//        this.title = title;
//        this.url = url;
//    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(this.url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
