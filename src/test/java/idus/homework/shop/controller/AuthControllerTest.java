package idus.homework.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.vo.SignupValidationError;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@DisplayName("인증 컨트롤러 테스트")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private WebApplicationContext wac;
    @Autowired private ObjectMapper objectMapper;

    private MemberSignupRequest request;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

        this.request = new MemberSignupRequest();
        request.setName("테스터code");
        request.setNickname("tester");
        request.setPassword("Abcd1234!@");
        request.setPhoneNum("01012345678");
        request.setEmail("testcode@gmail.com");
        request.setGender("F");
    }

    @Test
    @DisplayName("회원 가입 성공")
    @Transactional
    void signupSuccess() throws Exception {
        // when
        ResultActions action = mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        action.andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 가입 실패 - 이름 부적합")
    void nameValidationCheck() throws Exception {
        // given
        request.setName("테스터123");

        // when
        JSONArray errors = signupFail(request);

        // then
        String nameError = errors.getString(0);
        assertThat(nameError).isEqualTo(SignupValidationError.NAME_NON_CONFORM.value());

    }

    @Test
    @DisplayName("회원 가입 실패 - 별명 부적합")
    void nicknameValidationCheck() throws Exception {
        // given
        request.setNickname("TESTER");

        // when
        JSONArray errors = signupFail(request);

        // then
        String nameError = errors.getString(0);
        assertThat(nameError).isEqualTo(SignupValidationError.NICK_NAME_NON_CONFORM.value());

    }

    @Test
    @DisplayName("회원 가입 실패 - 비밀번호 부적합")
    void passwordValidationCheck() throws Exception {
        // given
        request.setPassword("AA123");

        // when
        JSONArray errors = signupFail(request);

        // then
        String nameError = errors.getString(0);
        assertThat(nameError).isEqualTo(SignupValidationError.PASSWORD_NON_CONFORM.value());

    }

    @Test
    @DisplayName("회원 가입 실패 - 전화번호 부적합")
    void phoneNumValidationCheck() throws Exception {
        // given
        request.setPhoneNum("010-1234-5678");

        // when
        JSONArray errors = signupFail(request);

        // then
        String nameError = errors.getString(0);
        assertThat(nameError).isEqualTo(SignupValidationError.PHONENUM_NON_CONFORM.value());

    }

    @Test
    @DisplayName("회원 가입 실패 - 이메일 부적합")
    void emailValidationCheck() throws Exception {
        // given
        request.setEmail("testcode");

        // when
        JSONArray errors = signupFail(request);

        // then
        String nameError = errors.getString(0);
        assertThat(nameError).isEqualTo(SignupValidationError.EMAIL_NON_CONFORM.value());

    }

    private JSONArray signupFail(MemberSignupRequest request) throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        JSONObject object = new JSONObject(result.getResponse().getContentAsString());
        return object.getJSONArray("errors");
    }
}