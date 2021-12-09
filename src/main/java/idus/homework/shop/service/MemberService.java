package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.*;
import idus.homework.shop.repository.MemberRepository;
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

    // 단일 회원의 주문 목록 조회
    public SearchOrderByEmailResponse findOrdersByEmail(String email) {
        Member findMember = memberRepository.findById(email).orElse(null);

        if (findMember != null) {
            SearchOrderByEmailResponse response = SearchOrderByEmailResponse.builder()
                    .email(findMember.getEmail())
                    .name(findMember.getName())
                    .build();

            List<Order> orders = findMember.getOrders();
            for (Order order : orders) {
                response.getOrders().add(new SearchOrder(order));
            }

            return response;
        }

        return null;
    }

    // 여러 회원 목록 조회
    public SearchMemberListResponse findMembers(String word, Pageable pageable) {
        List<Member> members = memberRepository.findAllByEmailContainsOrNameContains(word, word, pageable);

        List<SearchMemberWithLastOrder> response = new ArrayList<>();
        for (Member member : members) {
            SearchMemberWithLastOrder searchedMember = new SearchMemberWithLastOrder(member);
            response.add(searchedMember);
        }

        return new SearchMemberListResponse(response);
    }
}
