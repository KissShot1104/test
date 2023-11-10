package test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import test.domain.Member;
import test.web.SessionConst;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        if (member == null) {
            return "/home";
        }

        model.addAttribute("member", member);
        return "/loginHome";
    }
}
