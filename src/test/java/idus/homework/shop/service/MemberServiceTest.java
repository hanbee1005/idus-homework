package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.dto.SearchMemberListResponse;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void init() {
        MemberSignupRequest request1 = new MemberSignupRequest();
        request1.setName("테스터");
        request1.setNickname("tester");
        request1.setPassword("Abcd1234!@");
        request1.setPhoneNum("01012345678");
        request1.setEmail("test@gmail.com");
        request1.setGender("F");

        saveDumyMember(request1);

        MemberSignupRequest request2 = new MemberSignupRequest();
        request2.setName("홍길동");
        request2.setNickname("hong");
        request2.setPassword("Abcd1234!@");
        request2.setPhoneNum("01012345678");
        request2.setEmail("hong@gmail.com");
        request2.setGender("M");

        saveDumyMember(request2);

        MemberSignupRequest request3 = new MemberSignupRequest();
        request3.setName("김영희");
        request3.setNickname("yhkim");
        request3.setPassword("Abcd1234!@");
        request3.setPhoneNum("01012345678");
        request3.setEmail("yhkim@gmail.com");
        request3.setGender("F");

        saveDumyMember(request3);
    }

    private void saveDumyMember(MemberSignupRequest request) {
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

    @Test
    @DisplayName("단일 회원 상세 정보 조회 - 회원이 없는 경우")
    void notFoundMember() {
        // given
        String email = "testcode@gmail.com";

        // when
        // then
        assertThrows(IllegalStateException.class, () -> memberService.findMemberById(email));
    }

    @Test
    @DisplayName("여러 회원 목록 조회")
    void findMemberList() {
        // given
        String word = "@gmail.com";
        PageRequest pageRequest = PageRequest.of(0, 2);

        // when
        SearchMemberListResponse members = memberService.findMembers(word, pageRequest);

        // then
        assertThat(members.getMembers().size()).isEqualTo(2);
        assertThat(members.getMembers().get(0).getEmail()).contains(word);
        assertThat(members.getMembers().get(1).getEmail()).contains(word);
    }

}