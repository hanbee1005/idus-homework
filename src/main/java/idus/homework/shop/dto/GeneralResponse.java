package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "일반 응답 객체")
public class GeneralResponse {

    @Schema(description = "status")
    private int status;

    @Schema(description = "message")
    private String message;

    @Builder
    public GeneralResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
