package com.study.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //이 클래스가 API 핸들러라는 표시
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello(@RequestParam(defaultValue = "hello") String greeting) {
        return new HelloResponse(greeting + " spring", 1);
    }

    @GetMapping("/hello/{name}")
    public HelloResponse hello2(@PathVariable String name) {
        return new HelloResponse("hello " + name, 1);
    }

    @GetMapping("user/{id}")
    public String getUser(@PathVariable Long id) {
        return "요청한 사용자 id: " + id;
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "java") String keyword) {
        return "검색어: " + keyword;
    }

    public record HelloResponse(String message, int version) {}
}
