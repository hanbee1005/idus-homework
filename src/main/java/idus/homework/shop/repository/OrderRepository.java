package idus.homework.shop.repository;

import idus.homework.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByMemberEmail(String email);

    Order findFirstByMemberEmailOrderByCreatedDateDesc(String email);
}
