package idus.homework.shop.domain;

import idus.homework.shop.dto.MemberSignupRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String password;

    private String phoneNum;

    private String email;

    private String gender;

    @Builder
    public Member(MemberSignupRequest request) {
        name = request.getName();
        nickname = request.getNickname();
        password = request.getPassword();
        phoneNum = request.getPhoneNum();
        email = request.getEmail();
        gender = request.getGender();
    }

    // 비밀번호 암호화
    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
