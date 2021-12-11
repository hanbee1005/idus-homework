package idus.homework.shop.dto;

import idus.homework.shop.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private String orderId;

    private String itemName;

    private LocalDateTime payDt;

    public OrderResponse(Order order) {
        orderId = order.getId();
        itemName = order.getItemName();
        payDt = order.getPayDt();
    }
}
