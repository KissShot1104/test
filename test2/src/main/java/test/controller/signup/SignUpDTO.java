package test.controller.signup;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class SignUpDTO {

    @NotBlank(message = "아이디를 입력해주세요")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
}
