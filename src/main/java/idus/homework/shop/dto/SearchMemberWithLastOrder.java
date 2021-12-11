package idus.homework.shop.dto;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import lombok.Getter;

@Getter
public class SearchMemberWithLastOrder {

    private String email;

    private String name;

    private String nickname;

    private String phoneNum;

    private String gender;

    private OrderResponse lastOrder;

    public SearchMemberWithLastOrder(Member member, Order order) {
        email = member.getEmail();
        name = member.getName();
        nickname = member.getNickname();
        phoneNum = member.getPhoneNum();
        gender = member.getGender();

        if (order != null) {
            lastOrder = new OrderResponse(order);
        }
    }
}
