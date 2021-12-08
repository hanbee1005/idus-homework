package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "로그인 응답")
public class JwtResponse {

    @Schema(description = "액세스 토큰", example = "fhkahehfeilhfiahdhkfhel")
    private String token;
}
