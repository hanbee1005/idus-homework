package idus.homework.shop.controller;

import idus.homework.shop.dto.GeneralResponse;
import idus.homework.shop.dto.JwtRequest;
import idus.homework.shop.dto.JwtResponse;
import idus.homework.shop.dto.MemberSignupRequest;
import idus.homework.shop.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[001] Auth", description = "회원가입, 로그인")
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(description = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
    })
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody MemberSignupRequest request) {
        GeneralResponse response = authService.signup(request);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class)))
    })
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralResponse(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
