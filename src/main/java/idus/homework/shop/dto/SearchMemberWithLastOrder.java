package idus.homework.shop.dto;

import idus.homework.shop.domain.Member;
import lombok.Getter;

@Getter
public class SearchMemberWithLastOrder {

    private String email;

    private String name;

    private String nickname;

    private String password;

    private String phoneNum;

    private String gender;

    private String orderId;

    private String itemName;

    private String payDt;

    public SearchMemberWithLastOrder(Member member) {
        email = member.getEmail();
        name = member.getName();
        nickname = member.getNickname();
        password = member.getPassword();
        phoneNum = member.getPhoneNum();
        gender = member.getGender();
    }
}
