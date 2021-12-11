package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@Schema(description = "회원가입 요청")
public class MemberSignupRequest {

    @Schema(description = "이름", example = "테스터")
    @NotBlank
    @Pattern(regexp="^[a-zA-Z가-힣]+$", message = "이름은 한글, 영문 대소문자만 허용합니다.")
    @Size(max = 20, message = "이름은 최대 20자만 허용합니다.")
    private String name;

    @Schema(description = "별명", example = "tester")
    @NotBlank
    @Pattern(regexp="^[a-z]+$", message = "별명은 영문 소문자만 허용합니다.")
    @Size(max = 30, message = "별명은 최대 30자만 허용합니다.")
    private String nickname;

    @Schema(description = "비밀번호", example = "Abcd1234!@")
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,}",
            message = "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자를 각 1개 이상씩 포함하는 10자 이상의 문자여야 합니다.")
    private String password;

    @Schema(description = "전화번호", example = "01012345678")
    @NotBlank
    @Pattern(regexp="^[0-9]+$", message = "전화번호는 숫자만 허용합니다.")
    @Size(max = 20, message = "전화번호는 최대 20자만 허용합니다.")
    private String phoneNum;

    @Schema(description = "이메일", example = "test@gmail.com")
    @NotBlank
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 100, message = "이메일은 최대 100자만 허용합니다.")
    private String email;

    @Schema(description = "성별", example = "F")
    private String gender;
}
