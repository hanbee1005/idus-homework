package idus.homework.shop.dto;

import idus.homework.shop.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "단일 회원 주문 목록 응답")
public class SearchOrderByEmailResponse {

    @Schema(description = "주문자 이메일", example = "test@gmail.com")
    private String email;

    @Schema(description = "주문 목록")
    private List<SearchOrder> orders = new ArrayList<>();

    @Builder
    public SearchOrderByEmailResponse(String email, List<Order> orders) {
        this.email = email;

        for (Order order : orders) {
            this.orders.add(new SearchOrder(order));
        }
    }
}
