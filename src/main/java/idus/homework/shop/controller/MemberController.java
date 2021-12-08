package idus.homework.shop.controller;

import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[002] Member", description = "회원 조회")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(description = "단일 회원 상세 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = SearchMemberResponse.class)))
    })
    @GetMapping("{email}")
    public ResponseEntity<?> searchMember(@Schema(name = "이메일", example = "test@gmail.com") @PathVariable String email) {
        return ResponseEntity.ok(memberService.findMemberById(email));
    }
}
