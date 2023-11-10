package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.domain.Member;
import test.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).stream()
                .filter(member -> member.getPassword().equals(password))
                .findAny()
                .orElse(null);
    }
}
