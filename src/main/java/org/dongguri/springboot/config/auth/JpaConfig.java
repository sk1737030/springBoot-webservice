package org.dongguri.springboot.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig { }

/**
 * SpringApplicationd에 붙어있으면 테스트할 때 @WebMVC를 사용하면 에러가 난다 그래서 분리를 함.
 */
