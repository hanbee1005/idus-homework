package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.*;
import idus.homework.shop.repository.MemberRepository;
import idus.homework.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    // 단일 회원 상세 정보 조회
    public SearchMemberResponse findMemberById(String email) {
        Member member = memberRepository.findById(email).orElse(null);

        if (member == null) {
            throw new IllegalStateException("회원이 존재하지 않습니다.");
        }

        return SearchMemberResponse.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNum(member.getPhoneNum())
                .email(member.getEmail())
                .gender(member.getGender())
                .build();
    }

    // 여러 회원 목록 조회
    public SearchMemberListResponse findMembers(String word, Pageable pageable) {
        List<Member> members = memberRepository.findAllByEmailContainsOrNameContains(word, word, pageable);

        List<SearchMemberWithLastOrder> response = new ArrayList<>();
        for (Member member : members) {
            Order lastOrder = orderRepository.findFirstByMemberEmailOrderByCreatedDateDesc(member.getEmail());
            SearchMemberWithLastOrder searchedMember = new SearchMemberWithLastOrder(member, lastOrder);
            response.add(searchedMember);
        }

        return new SearchMemberListResponse(response);
    }

    public Member findByEmail(String email) {
        return memberRepository.findById(email).orElse(null);
    }
}
