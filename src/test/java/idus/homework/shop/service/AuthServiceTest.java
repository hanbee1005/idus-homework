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
        request.setName("테스터1");
        request.setNickname("tester");
        request.setPassword("1234");
        request.setPhoneNum("01012345678");
        request.setEmail("test@gmail.com");
        request.setGender("F");

        // when
        GeneralResponse response = authService.signup(request);

        // then
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("회원 가입 조건 확인")
    void validationCheck() {

    }

}