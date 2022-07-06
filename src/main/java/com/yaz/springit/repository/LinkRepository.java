package com.yaz.springit.repository;

import com.yaz.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {

    Link findByTitle(String title);


}
