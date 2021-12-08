package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.JwtRequest;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.repository.MemberRepository;
import idus.homework.shop.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public GeneralResponse signup(MemberSignupRequest request) {
        // TODO: Validation Check

        Member member = Member.builder().request(request).build();
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);

        return GeneralResponse.builder().status(200).message("회원가입 성공").build();
    }

    public GeneralResponse login(JwtRequest request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return GeneralResponse.builder().status(200).message("로그인 성공 (" + principal.getUsername() + ")").build();
    }
}
