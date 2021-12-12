package idus.homework.shop.controller;

import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.SearchMemberListResponse;
import idus.homework.shop.dto.SearchMemberResponse;
import idus.homework.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "[002] Member", description = "회원 조회")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(description = "단일 회원 상세 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 상세 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = SearchMemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "회원이 존재하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
    })
    @GetMapping("{email}")
    public ResponseEntity<?> searchMember(@Parameter(description = "이메일", example = "test@gmail.com") @PathVariable String email) {
        try {
            return ResponseEntity.ok(memberService.findMemberById(email));
        } catch(Exception e) {
            return new ResponseEntity<>(new GeneralResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(description = "여러 회원 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = SearchMemberListResponse.class)))
    })
    @GetMapping()
    public ResponseEntity<?> searchMemberList(
            @Parameter(description = "이메일 or 이름", example = "@gmail.com")
                @RequestParam(required = false, defaultValue = "") String word,
            @Parameter(description = "페이지", example = "0") @RequestParam int page,
            @Parameter(description = "크기", example = "2") @RequestParam int size) {
        return ResponseEntity.ok(memberService.findMembers(word, PageRequest.of(page, size)));
    }
}
