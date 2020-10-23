package com.lfg.spring.controller;

import com.lfg.spring.model.Member;
import com.lfg.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    
    @GetMapping("/members")
    public List<Member> getAll(){
        return memberService.getAll();
    }

    @GetMapping("/members/{groupId}")
    public ResponseEntity<List<Member>> getByGroupId(@PathVariable Long groupId){

        return new ResponseEntity<>(memberService.getByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping("/members/{groupId}")
    public ResponseEntity<Member> create(@PathVariable Long groupId){

        return new ResponseEntity<>(memberService.create(groupId), HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{groupId}")
    public ResponseEntity<?> delete(@PathVariable Long groupId) {

        memberService.delete(groupId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
