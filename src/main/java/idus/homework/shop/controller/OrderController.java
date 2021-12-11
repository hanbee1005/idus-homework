package idus.homework.shop.controller;

import idus.homework.shop.dto.SearchOrderByEmailResponse;
import idus.homework.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[003] Order", description = "주문 조회")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(description = "단일 회원 주문 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = SearchOrderByEmailResponse.class)))
    })
    @GetMapping()
    public ResponseEntity<?> searchOrdersByEmail(@Parameter(description = "이메일", example = "test@gmail.com") @RequestParam String email) {
        return ResponseEntity.ok(orderService.findOrdersByEmail(email));
    }
}
