package org.dongguri.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.dongguri.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profiles").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}

/**
 * @EnableWebSecurity
 * - Spring Security 설정을 활성화시켜준다
 * @csrf.disable.headers.farmeoptions.disable
 * - h2-console 화면을 사용하기 위해 해당 옵션들을 disable
 * @authorizeRequets
 * - url별 권한 관리를 설정하는 옵션
 * - authorizeRequests가 선언되어야면 antMatchers 옵션을 사용가능
 *
 * @oauth2Login
 * -oauth2 로그인 기능에 대한 여러 설정의 진입점
 *
 * @userInfoEndpoint
 * -OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
 *
 * @UserService
 * - 소셜 로그인 성공시 후속 조치를 진행할 user service 인터페이스의 구현체를 등록 합니다.
 * - 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는기능들을 명시 할 수 있다.
 */
