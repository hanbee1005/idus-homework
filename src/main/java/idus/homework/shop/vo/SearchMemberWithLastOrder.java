package idus.homework.shop.vo;

import idus.homework.shop.domain.Member;
import idus.homework.shop.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "여러 회원 목록 조회 응답")
public class SearchMemberWithLastOrder {

    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "이름", example = "테스터")
    private String name;

    @Schema(description = "별명", example = "tester")
    private String nickname;

    @Schema(description = "전화번호", example = "01012345678")
    private String phoneNum;

    @Schema(description = "성별", example = "F")
    private String gender;

    @Schema(description = "마지막 주문 정보")
    private SearchOrder lastOrder;

    public SearchMemberWithLastOrder(Member member, Order order) {
        email = member.getEmail();
        name = member.getName();
        nickname = member.getNickname();
        phoneNum = member.getPhoneNum();
        gender = member.getGender();

        if (order != null) {
            lastOrder = new SearchOrder(order);
        }
    }
}
