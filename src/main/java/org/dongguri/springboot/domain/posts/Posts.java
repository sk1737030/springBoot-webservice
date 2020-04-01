package org.dongguri.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
/**
 * @Entity
 * - 테이블과 링크될 클래스임을 나타낸다
 * - 기본값을 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다.
 * @GeneratedValue
 * -PK의 생성규칙을 나타낸다
 * 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야함 auto_incrementrk 된다
 *
 * @Column
 * - 테이블의 칼람을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬림이된다.
 * - 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션ㅇ ㅣ있을 때 사용
 * varchar(255)가 기본값인데 사이즈를 500으로 늘리고 싶거나 타입을 text로 변경하고 싶거나 경우 사용
 * @NoArgsConstrucotr
 * - 기본생성자 자동 추가
 * - public Posts() {}와 같은효과
 * - b8ilder
 * - 빌더패턴 클래스 생성
 * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
 *
 */
