package idus.homework.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    private String itemName;

    private LocalDateTime payDt;

    @Builder
    public Order(String id, String itemName) {
        this.id = id;
        this.itemName = itemName;
        this.payDt = LocalDateTime.now();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}