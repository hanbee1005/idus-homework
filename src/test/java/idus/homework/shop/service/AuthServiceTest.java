package idus.homework.shop.service;

import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.MemberSignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired AuthService authService;

    @Test
    @DisplayName("회원 가입 성공")
    void signup() {
        // given
        MemberSignupRequest request = new MemberSignupRequest();
        request.setName("테스터code");
        request.setNickname("tester");
        request.setPassword("Abcd1234!@");
        request.setPhoneNum("01012345678");
        request.setEmail("testcode@gmail.com");
        request.setGender("F");

        // when
        GeneralResponse response = authService.signup(request);

        // then
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("회원 가입 실패 - 중복 회원")
    void duplicateMember() {
        // given
        MemberSignupRequest request1 = new MemberSignupRequest();
        request1.setName("테스터code");
        request1.setNickname("tester");
        request1.setPassword("Abcd1234!@");
        request1.setPhoneNum("01012345678");
        request1.setEmail("testcode@gmail.com");
        request1.setGender("F");

        MemberSignupRequest request2 = new MemberSignupRequest();
        request2.setName("code테스터");
        request2.setNickname("tester");
        request2.setPassword("Abcd1234!@");
        request2.setPhoneNum("01012345678");
        request2.setEmail("testcode@gmail.com");
        request2.setGender("M");

        // when
        GeneralResponse response = authService.signup(request1);
        assertThat(response.getStatus()).isEqualTo(200);

        // then
        assertThrows(IllegalStateException.class, () -> {
            authService.signup(request2);
        });
    }

}