package com.yaz.springit.repository;

import com.yaz.springit.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommetRepository extends JpaRepository<Comment,Long> {

}
