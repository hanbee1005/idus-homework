package idus.homework.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    private String itemName;

    private LocalDateTime payDt;

    @Builder
    public Order(String itemName, String email) {
        makeRandomOrderId();
        this.itemName = itemName;
        this.payDt = LocalDateTime.now();
        setCreatedBy(email);
    }
    
    private void makeRandomOrderId() {
        Random rnd =new Random();
        StringBuffer buf =new StringBuffer();

        for(int i = 0; i < 12; i++){
            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴.
            // true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+65));
            }else{
                buf.append((rnd.nextInt(10)));
            }
        }

        this.id = buf.toString();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
