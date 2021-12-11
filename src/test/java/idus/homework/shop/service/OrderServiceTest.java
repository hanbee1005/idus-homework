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
        MemberSignupRequest request = new MemberSignupRequest();
        request.setName("테스터1");
        request.setNickname("tester");
        request.setPassword("1234");
        request.setPhoneNum("01012345678");
        request.setEmail("test1@gmail.com");
        request.setGender("F");

        Member member = Member.builder().request(request).build();
        memberRepository.save(member);

        makeOrders();
    }

    @Test
    @DisplayName("단일 회원 주문 목록 조회")
    void findOrders() {
        // given
        String email = "test1@gmail.com";

        // when
        SearchOrderByEmailResponse response = orderService.findOrdersByEmail(email);

        // then
        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getOrders().size()).isEqualTo(2);
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
