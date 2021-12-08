package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "단일 회원 상세 정보 조회")
public class SearchMemberResponse {

    @Schema(description = "이름", example = "테스터")
    private String name;

    @Schema(description = "별명", example = "tester")
    private String nickname;

    @Schema(description = "전화번호", example = "01012345678")
    private String phoneNum;

    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "성별", example = "F")
    private String gender;

    @Builder
    public SearchMemberResponse(String name, String nickname, String phoneNum, String email, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.gender = gender;
    }
}
