package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor  // 매개변수가 없는 constructor -> 기본 생성자 생성
@Entity     // 테이블과 링크될 클래스임을 나타냄 (ex.SalesManager.java -> sales_manager table)
public class Posts extends BaseTimeEntity {

    @Id     // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)     // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 됨
    private String title;                       // 하지만 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스 생성 (생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // update 기능에서 DB에 쿼리를 날리는 부분이 없음 <- JPA의 영속성 컨텍스트 때문! --- dirty checking
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
