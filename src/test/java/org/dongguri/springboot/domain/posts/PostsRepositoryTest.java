package org.dongguri.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                .title(title)
                                .author("sk1838030@naver.com")
                                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}


/**
 * postsRepository.save
 * - 테이블에 posts에 insert/update 쿼리를 실행한다
 * - id값이있다면 update, 없다면 insert
 *
 * postsRepositroy.findall
 * - 테이블에 posts 에있는 모든 데이터를 조회해옴
 *
 * 별다른 설정 없이 @SpringBootText 사용할 경우 h2 데이터베이스를 자동실행한다.
 * */
