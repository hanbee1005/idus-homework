package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(description = "회원가입 요청")
public class MemberSignupRequest {

    @Schema(description = "이름", example = "테스터")
    private String name;

    @Schema(description = "별명", example = "tester")
    private String nickname;

    @Schema(description = "비밀번호", example = "abcd1234ef")
    private String password;

    @Schema(description = "전화번호", example = "01012345678")
    private String phoneNum;

    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "성별", example = "F")
    private String gender;
}
