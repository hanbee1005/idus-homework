package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.dto.SearchOrderByEmailResponse;
import idus.homework.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberService memberService;

    private final OrderRepository orderRepository;

    /**
     * 단일 회원의 주문 목록 조회
     * @param email
     * @return
     */
    public SearchOrderByEmailResponse findOrdersByEmail(String email) {
        Member member = memberService.findByEmail(email);

        if (member == null) {
            throw new IllegalStateException("회원이 존재하지 않습니다.");
        }

        List<Order> orders = orderRepository.findOrdersByEmail(member);
        return new SearchOrderByEmailResponse(email, orders);
    }

    /**
     * 주문하기
     * @param itemName
     * @return
     */
    public String saveOrder(String itemName) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Order order = Order.builder()
                .itemName(itemName)
                .email(email)
                .build();

        Member member = memberService.findByEmail(email);
        order.setMember(member);

        orderRepository.save(order);

        return order.getId();
    }
}
