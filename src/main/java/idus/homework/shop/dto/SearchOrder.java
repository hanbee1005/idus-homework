package idus.homework.shop.dto;

import idus.homework.shop.domain.Order;

import java.time.LocalDateTime;

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
