package idus.homework.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSignupRequest {

    private String name;
    private String nickname;
    private String password;
    private String phoneNum;
    private String email;
    private String gender;
}
