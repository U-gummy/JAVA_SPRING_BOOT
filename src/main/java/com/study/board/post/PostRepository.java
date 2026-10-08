package com.study.board.post;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository : 테이블에 데이터를 넣고 꺼내는 도구

// JpaRepository: Spring Data JPA가 제공하는 인터페이스. 저장, 조회, 삭제 같은 메서드가 이미 정의되어있음.
// <Post, Long> : <어떤 Entity를 다루는지, Entity의 id(@Id) 타입>
public interface PostRepository extends JpaRepository<Post, Long> {
}
