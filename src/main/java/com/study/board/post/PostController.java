package com.study.board.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts") //공통 경로 묶기
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) { // 추가
        this.postRepository = postRepository;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> list() {
        List<Post> posts = postRepository.findAll();

        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            responses.add(new PostResponse(post.getId(), post.getTitle(), post.getContent()));
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> detail(@PathVariable Long id) {
        Optional<Post> result = postRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = result.get();
        PostResponse response = new PostResponse(post.getId(), post.getTitle(), post.getContent());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostCreateRequest request) {
        Post post = new Post(request.title(), request.content());
        Post saved = postRepository.save(post);
        PostResponse response = new PostResponse(saved.getId(), saved.getTitle(), saved.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
