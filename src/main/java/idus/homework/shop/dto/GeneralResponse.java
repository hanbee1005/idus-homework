package idus.homework.shop.dto;

import lombok.Builder;

public class GeneralResponse {

    private int status;
    private String message;

    @Builder
    public GeneralResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
