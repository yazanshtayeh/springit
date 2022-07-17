package com.yaz.springit;

import com.yaz.springit.domain.Comment;
import com.yaz.springit.domain.Link;
import com.yaz.springit.repository.CommentRepository;
import com.yaz.springit.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
		System.out.println("welcome to spring it");
	}

	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
		return args -> {
			Link link=new Link("getting started with spring boot 2","https://springit.com");
			linkRepository.save(link);

			Comment comment=new Comment("this spring boot 2 link is great",link);
			commentRepository.save(comment);

			link.addComment(comment);

			System.out.println("we just inserted a link and comment");
			System.out.println("==================================================================");
		};
	}
}
