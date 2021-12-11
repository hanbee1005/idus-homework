package idus.homework.shop.repository;

import idus.homework.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

    Order findFirstByMemberEmailOrderByCreatedDateDesc(String email);
}
