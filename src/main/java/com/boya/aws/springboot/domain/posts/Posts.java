package com.boya.aws.springboot.domain.posts;

import com.boya.aws.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // JPA 어노테이션 , 테이블과 링크될 클래스임을 나타냄, 기본값으로 카멜 케이스 네이밍을 언더스코어 네이밍으로 바꿔 테이블 이름 매칭
public class Posts extends BaseTimeEntity {

    @Id // PK 필드
    @GeneratedValue ( strategy = GenerationType.IDENTITY) // PK 생성 규칙, GenerationType.IDENTITY 는 AutoIncrement 위해 넣음
    private Long id;

    @Column ( length = 500, nullable = false) // 컬럼을 나타냄. 굳이 쓸 필요는 없으나 기본값 외에 변경이 필요한  옵션 있으면 사용
    private String title;

    @Column ( columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성 , 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
