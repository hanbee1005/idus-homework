package idus.homework.shop.service;

import idus.homework.shop.domain.Order;
import idus.homework.shop.dto.SearchOrderByEmailResponse;
import idus.homework.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 단일 회원의 주문 목록 조회
    public SearchOrderByEmailResponse findOrdersByEmail(String email) {
        List<Order> orders = orderRepository.findByMemberEmail(email);
        return new SearchOrderByEmailResponse(email, orders);
    }
}
