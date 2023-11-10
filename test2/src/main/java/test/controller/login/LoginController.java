package test.controller.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.web.SessionConst;
import test.domain.Member;
import test.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(LoginDTO loginDTO) {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDTO loginDTO,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member member = loginService.login(loginDTO.getLoginId(), loginDTO.getPassword());

        if (member == null) {
            return "/login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "redirect:" + redirectURI;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
