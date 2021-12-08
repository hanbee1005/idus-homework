package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void init() {
        MemberSignupRequest request = new MemberSignupRequest();
        request.setName("테스터");
        request.setNickname("tester");
        request.setPassword("1234");
        request.setPhoneNum("01012345678");
        request.setEmail("test@gmail.com");
        request.setGender("F");

        Member member = Member.builder().request(request).build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회")
    void findMemberById() {
        // given
        String email = "test@gmail.com";

        // when
        SearchMemberResponse findMember = memberService.findMemberById(email);

        // then
        assertThat(findMember.getEmail()).isEqualTo(email);
    }

}