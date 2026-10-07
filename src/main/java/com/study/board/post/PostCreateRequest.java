package com.study.board.post;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public record PostCreateRequest(String title, String content) {
}
