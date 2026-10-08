package com.study.board.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //"이 클래스는 테이블과 연결된다"는 표시
public class Post {

    @Id //이 필드가 **행을 구분하는 고유 번호(기본 키)**라는 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 값을 DB가 자동으로 1, 2, 3... 순서대로
    private Long id;

    private String title;

    private String content;

    //JPA가 DB에서 데이터를 꺼내 객체로 만들 때, 먼저 빈 객체를 만들고 값을 채워 넣음.
    //파라미터 없는 빈 생성자가 꼭 필요
    protected Post() {

    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
