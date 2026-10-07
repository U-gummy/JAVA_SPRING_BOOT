package com.study.board.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts") //공통 경로 묶기
public class PostController {

    @GetMapping
    public String list() {
        return "게시글 목록";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> detail(@PathVariable Long id) {
        if (id == 1L) {
            PostResponse response = new PostResponse(1L, "샘플 글", "샘플 내용");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostCreateRequest request) {
        PostResponse response = new PostResponse(1L, request.title(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
