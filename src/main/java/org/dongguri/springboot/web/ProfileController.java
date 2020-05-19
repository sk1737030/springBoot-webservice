package org.dongguri.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

        @GetMapping("/profile")
        public String profile() {
            List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        System.out.println("defaultProfile: " + defaultProfile);
        System.out.println("defaultProfㅁㄴㅇㅁㄴㅇile: " + env.getActiveProfiles());

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}

/**
 * env.getActiveProfiles()
 * - 현재 실행 중인 ActiveProfile을 모두 가져온다.
0 * - 여기서 real, eral1, real2, 는 모두 배포에 사용될 profile이라 이 중 하라도 있으면 그 값을 반환하도록 한다.
 * - 실제로 이번 무중단 배포에서는 real1, real2 만 사용되지만 step2를 다시 사용해 볼수도 있으니 real도 남겨둔다.
 */
