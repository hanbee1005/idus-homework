package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public GeneralResponse signup(MemberSignupRequest request) {
        // TODO: Validation Check

        Member member = Member.builder().request(request).build();
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);

        return GeneralResponse.builder().status(200).message("회원가입 성공").build();
    }
}
