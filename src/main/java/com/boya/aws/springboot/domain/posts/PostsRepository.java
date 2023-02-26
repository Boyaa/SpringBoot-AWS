package com.boya.aws.springboot.domain.posts;

import com.boya.aws.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
// JpaRepository<Entity 클래스, PK타입> 상속하면 기본 CRUD 메소드 자동 생성
// Entity 클래스와 EntityRepository는 같은 패키지에 위치해야 함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // JPA에서 제공하지 않는 메소드를 쿼리로 작성할 수 있게 해줌
    List<Posts> findAllDesc();
}
