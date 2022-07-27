package com.yaz.springit.controller;


import com.yaz.springit.domain.Link;
import com.yaz.springit.domain.Vote;
import com.yaz.springit.repository.LinkRepository;
import com.yaz.springit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID,@PathVariable short direction,@PathVariable int voteCount){
        Optional<Link> optionalLink= linkRepository.findById(linkID);
        if(optionalLink.isPresent()){
            Link link=optionalLink.get();
            Vote vote=new Vote(direction,link);
            voteRepository.save(vote);

            int updateCount=voteCount+direction;

            link.setVoteCount(updateCount);
            linkRepository.save(link);
            return updateCount;
        }
        else {
            return voteCount;
        }
    }
}
