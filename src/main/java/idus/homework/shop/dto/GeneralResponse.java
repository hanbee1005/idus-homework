package idus.homework.shop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GeneralResponse {

    private int status;
    private String message;

    @Builder
    public GeneralResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
