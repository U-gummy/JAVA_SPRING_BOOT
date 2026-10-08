package com.study.board.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberCreateRequest request) {
        MemberResponse response = new MemberResponse(1L, request.name(), request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> detail(@PathVariable Long id) {
        if (id == 1L) {
            MemberResponse response = new MemberResponse(id, "홍길동", "hong@email.com");
            return ResponseEntity.status(HttpStatus.OK).body(response);
//            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        return ResponseEntity.notFound().build();


    }

}
