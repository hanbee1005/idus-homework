package idus.homework.shop.dto;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidateErrorResponse {
    private List<String> errors = new ArrayList<>();
}
