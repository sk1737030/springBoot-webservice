package org.dongguri.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p from Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}

/**
 * Spring DataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 된다.
 *
 * Entity 클래스로만 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
 * Querydsl, jooq, Mybatis 위 3가지로 조회화고
 * 등록/수정/삭제는 jpa를 통해서 한다.
 **/