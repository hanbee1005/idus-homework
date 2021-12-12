package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.MemberSignupRequest;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void init() {
        makeMember();
        makeOrders();
    }

    @Test
    @DisplayName("단일 회원 주문 목록 조회 - 주문 목록 o")
    void findOrders() {
        // given
        String email = "test1@gmail.com";

        // when
        SearchOrderByEmailResponse response = orderService.findOrdersByEmail(email);

        // then
        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getOrders().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("단일 회원 주문 목록 조회 - 주문 목록 x")
    void findOrdersEmpty() {
        // given
        String email = "codetest@gmail.com";

        // when
        SearchOrderByEmailResponse response = orderService.findOrdersByEmail(email);

        // then
        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getOrders().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("단일 회원 주문 목록 조회 - 회원이 없는 경우")
    void notFoundMember() {
        // given
        String email = "testcode@gmail.com";

        // when
        // then
        assertThrows(IllegalStateException.class, () -> orderService.findOrdersByEmail(email));
    }

    private void makeMember() {
        MemberSignupRequest request1 = new MemberSignupRequest();
        request1.setName("테스터code");
        request1.setNickname("tester");
        request1.setPassword("Abcd1234!@");
        request1.setPhoneNum("01012345678");
        request1.setEmail("test1@gmail.com");
        request1.setGender("F");

        saveMember(request1);

        MemberSignupRequest request2 = new MemberSignupRequest();
        request2.setName("code테스터");
        request2.setNickname("tester");
        request2.setPassword("Abcd1234!@");
        request2.setPhoneNum("01012345678");
        request2.setEmail("codetest@gmail.com");
        request2.setGender("M");

        saveMember(request2);
    }

    private void saveMember(MemberSignupRequest request) {
        Member member = Member.builder().request(request).build();
        memberRepository.save(member);
    }

    private void makeOrders() {
        Order order1 = Order.builder()
                .itemName("노트북")
                .email("test1@gmail.com")
                .build();

        saveOrder(order1);

        Order order2 = Order.builder()
                .itemName("스텐드")
                .email("test1@gmail.com")
                .build();

        saveOrder(order2);
    }

    private void saveOrder(Order order) {
        Member member = memberRepository.findById(order.getCreatedBy()).get();
        order.setMember(member);

        orderRepository.save(order);
    }
}
