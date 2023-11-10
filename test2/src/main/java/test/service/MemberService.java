package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.controller.signup.SignUpDTO;
import test.domain.Member;
import test.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long signUp(SignUpDTO signUpDTO) {
        signUpDTO.getLoginId();
        Member member = Member.builder()
                .loginId(signUpDTO.getLoginId())
                .password(signUpDTO.getPassword())
                .nickname(signUpDTO.getNickname())
                .email(signUpDTO.getEmail())
                .build();

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> duplicateNickname = memberRepository.findByNickname(member.getNickname());
        if (!duplicateNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다..");
        }

        List<Member> duplicateLoginId = memberRepository.findByLoginId(member.getLoginId());
        if (!duplicateLoginId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
