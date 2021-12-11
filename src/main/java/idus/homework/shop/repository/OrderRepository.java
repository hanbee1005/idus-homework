package idus.homework.shop.repository;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("select o from Order o join fetch o.member m where m = :member")
    List<Order> findOrdersByEmail(@Param("member") Member member);

    Order findFirstByMemberEmailOrderByCreatedDateDesc(String email);
}
