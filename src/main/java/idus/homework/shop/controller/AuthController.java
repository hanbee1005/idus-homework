package idus.homework.shop.controller;

import idus.homework.shop.dto.*;
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
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
                    content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
            @ApiResponse(responseCode = "400", description = "회원가입 실패",
                    content = @Content(schema = @Schema(implementation = ValidateErrorResponse.class)))
    })
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody @Valid MemberSignupRequest request, Errors errors) {

        if (errors.hasErrors()) {
            ValidateErrorResponse response = new ValidateErrorResponse();
            for (ObjectError objectError : errors.getAllErrors()) {
                response.getErrors().add(objectError.getDefaultMessage());
                log.error(objectError.getDefaultMessage());
            }

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            GeneralResponse response = authService.signup(request);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(new GeneralResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(description = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "로그인 실패",
                    content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
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
