package org.dongguri.springboot.web;

import lombok.RequiredArgsConstructor;
import org.dongguri.springboot.config.auth.LoginUser;
import org.dongguri.springboot.config.auth.dto.SessionUser;
import org.dongguri.springboot.domain.user.User;
import org.dongguri.springboot.service.posts.PostsService;
import org.dongguri.springboot.web.dto.PostsResponseDto;
import org.dongguri.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}


/**
 * @Model 서버 템플릿 엔진에서 사용할 수 있다.
 * findallDesc()로 가져온 결과를 posts로 index.mustach로 전달한다.
 */