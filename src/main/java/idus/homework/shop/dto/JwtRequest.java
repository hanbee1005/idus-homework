package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "로그인 요청")
public class JwtRequest {

    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "비밀번호", example = "Abcd1234!@")
    private String password;
}
