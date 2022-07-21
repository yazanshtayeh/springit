package com.yaz.springit.controller;


import com.yaz.springit.domain.Link;
import com.yaz.springit.repository.LinkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/links")
public class LinkController {
    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @RequestMapping("/")
    // list
    public List<Link> list() {
        return linkRepository.findAll();
    }
    // CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable Long id) {
        return linkRepository.findById(id);
    }
    @PutMapping("/{id}")
    public Link update(@PathVariable Long id,@ModelAttribute Link link) {
//        Link linkToUpdate=linkRepository.getById(id);
//        linkToUpdate.setComments(link.getComments());
//        linkToUpdate.setTitle(link.getTitle());
        return linkRepository.save(link);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
         linkRepository.deleteById(id);
    }
}
