package idus.homework.shop.service;

import idus.homework.shop.domain.Member;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.dto.SearchMemberWithLastOrder;
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

import java.awt.print.Pageable;
import java.util.List;

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
        MemberSignupRequest request1 = new MemberSignupRequest();
        request1.setName("테스터");
        request1.setNickname("tester");
        request1.setPassword("1234");
        request1.setPhoneNum("01012345678");
        request1.setEmail("test@gmail.com");
        request1.setGender("F");

        saveDumyMember(request1);

        MemberSignupRequest request2 = new MemberSignupRequest();
        request2.setName("홍길동");
        request2.setNickname("hong");
        request2.setPassword("1234");
        request2.setPhoneNum("01012345678");
        request2.setEmail("hong@gmail.com");
        request2.setGender("M");

        saveDumyMember(request2);

        MemberSignupRequest request3 = new MemberSignupRequest();
        request3.setName("김영희");
        request3.setNickname("yhkim");
        request3.setPassword("1234");
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
    @DisplayName("여러 회원 목록 조회")
    void findMemberList() {
        // given
        String word = "@gmail.com";
        PageRequest pageRequest = PageRequest.of(0, 2);

        // when
        List<SearchMemberWithLastOrder> members = memberService.findMembers(word, pageRequest);

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members.get(0).getEmail()).contains(word);
        assertThat(members.get(1).getEmail()).contains(word);
    }

}