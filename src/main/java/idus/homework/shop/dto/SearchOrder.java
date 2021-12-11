package idus.homework.shop.dto;

import idus.homework.shop.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "주문 정보")
public class SearchOrder {

    @Schema(description = "주문번호", example = "AG4739GW26T1")
    private String id;

    @Schema(description = "제품명", example = "노트북")
    private String itemName;

    @Schema(description = "결제일시")
    private LocalDateTime payDt;

    public SearchOrder(Order order) {
        id = order.getId();
        itemName = order.getItemName();
        payDt = order.getPayDt();
    }
}
