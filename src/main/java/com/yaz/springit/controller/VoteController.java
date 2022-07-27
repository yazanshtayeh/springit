package com.yaz.springit.controller;


import com.yaz.springit.domain.Link;
import com.yaz.springit.domain.Vote;
import com.yaz.springit.service.LinkService;
import com.yaz.springit.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteService voteService;
    private LinkService linkService;

    public VoteController(VoteService voteService, LinkService linkService) {
        this.voteService = voteService;
        this.linkService = linkService;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID,@PathVariable short direction,@PathVariable int voteCount){
        Optional<Link> optionalLink= linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link=optionalLink.get();
            Vote vote=new Vote(direction,link);
            voteService.save(vote);

            int updateCount=voteCount+direction;

            link.setVoteCount(updateCount);
            linkService.save(link);
            return updateCount;
        }
        else {
            return voteCount;
        }
    }
}
