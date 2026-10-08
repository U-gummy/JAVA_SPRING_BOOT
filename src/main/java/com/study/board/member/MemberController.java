package com.study.board.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberCreateRequest request) {
        Member member = new Member(request.name(), request.email());
        Member saved = memberRepository.save(member);

        MemberResponse response = new MemberResponse(saved.getId(), saved.getName(), saved.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> list() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponse> responses = new ArrayList<>();

        for (Member member : members) {
            responses.add(new MemberResponse(member.getId(), member.getName(), member.getEmail()));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> detail(@PathVariable Long id) {
        Optional<Member> result = memberRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member member = result.get();

        MemberResponse response = new MemberResponse(member.getId(), member.getName(), member.getEmail());

        return ResponseEntity.ok(response);
    }

}
