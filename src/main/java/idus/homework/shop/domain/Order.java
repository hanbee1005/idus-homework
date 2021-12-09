package idus.homework.shop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @Column(name = "order_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "member_email")
    private Member member;

    private String itemName;

    private LocalDateTime payDt;
}
