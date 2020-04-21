package org.dongguri.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}

/**
 * @Target
 * -이 어노테이션이 생성될 수 있는 위치를 선정
 * - Parameter로 지정햇으니 메소드의 파라미터로 지정된 객체에서면 사용 할 수 있다.
 *
 * @interface
 * LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면된다.
 */


