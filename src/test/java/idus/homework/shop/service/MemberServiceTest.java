package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.dto.SearchMemberListResponse;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.dto.SearchOrderByEmailResponse;
import idus.homework.shop.repository.MemberRepository;
import idus.homework.shop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Autowired OrderRepository orderRepository;

    @BeforeEach
    void init() {
        MemberSignupRequest request1 = new MemberSignupRequest();
        request1.setName("테스터");
        request1.setNickname("tester");
        request1.setPassword("1234");
        request1.setPhoneNum("01012345678");
        request1.setEmail("test@gmail.com");
        request1.setGender("F");

        saveDumyMember(request1);

        MemberSignupRequest request2 = new MemberSignupRequest();
        request2.setName("홍길동");
        request2.setNickname("hong");
        request2.setPassword("1234");
        request2.setPhoneNum("01012345678");
        request2.setEmail("hong@gmail.com");
        request2.setGender("M");

        saveDumyMember(request2);

        MemberSignupRequest request3 = new MemberSignupRequest();
        request3.setName("김영희");
        request3.setNickname("yhkim");
        request3.setPassword("1234");
        request3.setPhoneNum("01012345678");
        request3.setEmail("yhkim@gmail.com");
        request3.setGender("F");

        saveDumyMember(request3);
    }

    private void saveDumyMember(MemberSignupRequest request) {
        Member member = Member.builder().request(request).build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회")
    void findMemberById() {
        // given
        String email = "test@gmail.com";

        // when
        SearchMemberResponse findMember = memberService.findMemberById(email);

        // then
        assertThat(findMember.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("단일 회원 주문 목록 조회")
    void findOrders() {
        // given
        makeOrders();
        String email = "test@gmail.com";

        // when
        SearchOrderByEmailResponse response = memberService.findOrdersByEmail(email);

        // then
        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getOrders().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("여러 회원 목록 조회")
    void findMemberList() {
        // given
        String word = "@gmail.com";
        PageRequest pageRequest = PageRequest.of(0, 2);

        // when
        SearchMemberListResponse members = memberService.findMembers(word, pageRequest);

        // then
        assertThat(members.getMembers().size()).isEqualTo(2);
        assertThat(members.getMembers().get(0).getEmail()).contains(word);
        assertThat(members.getMembers().get(1).getEmail()).contains(word);
    }

    private void makeOrders() {
        Order order1 = Order.builder()
                .id("AAAAAA1234BB")
                .itemName("노트북")
                .build();

        saveOrder(order1, "test@gmail.com");

        Order order2 = Order.builder()
                .id("BBBBBB1234CC")
                .itemName("스텐드")
                .build();

        saveOrder(order2, "test@gmail.com");

        Order order3 = Order.builder()
                .id("CCCCCC1234DD")
                .itemName("노트북")
                .build();

        saveOrder(order3, "hong@gmail.com");
    }

    private void saveOrder(Order order, String email) {
        Member member = memberRepository.findById(email).get();
        order.addMember(member);

        orderRepository.save(order);
    }

}