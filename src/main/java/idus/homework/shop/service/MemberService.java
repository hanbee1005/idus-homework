package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 단일 회원 상세 정보 조회
    public SearchMemberResponse findMemberById(String email) {
        Member member = memberRepository.findById(email).orElse(null);

        if (member != null) {
            return SearchMemberResponse.builder()
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .phoneNum(member.getPhoneNum())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .build();
        }

        return null;
    }
}
