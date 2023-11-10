package test.controller.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.domain.Member;
import test.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignUpController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpForm(SignUpDTO signUpDTO) {
        return "/members/signUpForm";
    }

    @PostMapping("/signup")
    public String signUp(@Validated @ModelAttribute SignUpDTO signUpDTO,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/members/signUpForm";
        }

        memberService.signUp(signUpDTO);

        return "redirect:/";
    }
}
