package idus.homework.shop.dto;

import idus.homework.shop.domain.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchOrder {

    private String id;

    private String itemName;

    private LocalDateTime payDt;

    public SearchOrder(Order order) {
        id = order.getId();
        itemName = order.getItemName();
        payDt = order.getPayDt();
    }
}
