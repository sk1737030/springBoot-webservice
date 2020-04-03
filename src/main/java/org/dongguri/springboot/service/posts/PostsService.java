package org.dongguri.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.dongguri.springboot.domain.posts.Posts;
import org.dongguri.springboot.domain.posts.PostsRepository;
import org.dongguri.springboot.web.dto.PostsListResponseDto;
import org.dongguri.springboot.web.dto.PostsResponseDto;
import org.dongguri.springboot.web.dto.PostsSaveRequestDto;
import org.dongguri.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당사용자가 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)  // --> .map(posts -> new PostsListResponseDto(posts)) 와 같다.
                .collect(Collectors.toList());

    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);

    }

}


/**
 * 일반적으로 스피링에서 Bean을 주입받는 방식들은 3가지가 있다
 * 1. @Autowired
 * 2. @setter
 * 3. 생성자
 * 가장 권장하는 방식이 생성자로 주입받는 방식이다. (@Autowired 권장하지 않는다).
 * 즉 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 받을 수 있다.
 * 요기서는 @{@link lombok.RequiredArgsConstructor} 가 해준다.
 *
 * 따로 UPdate를 날리는 부분이 없는데 그이유는 JPA의 영속성 컨텍스트 때문이다.
 * 영속성 컨텍스트란 엔티티를 영구 저장하는 환경이다.
 * JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
 * 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
 * 별도로 update 쿼리를 날릴 필요가 없다
 * 이 개념을 더티 체킹이라 한다.
 *
 * readOnlt = true 를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
 *
 * postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponsDto 변환 -> list로 반환하는 메소드이다.
 */