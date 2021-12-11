package idus.homework.shop.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SearchOrderByEmailResponse {
    private String email;

    private String name;

    private List<SearchOrder> orders = new ArrayList<>();

    @Builder
    public SearchOrderByEmailResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
