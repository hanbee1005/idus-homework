package idus.homework.shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "idus homework",
                version = "v1",
                description = "[손한비]홈워크_JAVA",
                contact = @Contact(name = "Hanbee Son",
                        email = "sonhanbi1002@gmail.com",
                        url = "https://github.com/hanbee1005"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "local-server"),
        },
        security = {
                @SecurityRequirement(name = "bearerToken"),
        }
)

@SecuritySchemes({
        @SecurityScheme(
                name = "bearerToken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        ),
})
@Configuration
public class SwaggerConfig {
    String[] loginPaths = {"/auth/**"};
    String[] memberPaths = {"/members/**"};
    String[] orderPaths = {"/orders/**"};
    String[] allPaths = {"/**"};

    @Bean
    public GroupedOpenApi authOpenApi() {
        return GroupedOpenApi.builder().group("auth-API").pathsToMatch(loginPaths).build();
    }

    @Bean
    public GroupedOpenApi memberOpenApi() {
        return GroupedOpenApi.builder().group("member-API").pathsToMatch(memberPaths).build();
    }

    @Bean
    public GroupedOpenApi orderOpenApi() {
        return GroupedOpenApi.builder().group("order-API").pathsToMatch(orderPaths).build();
    }

    @Bean
    public GroupedOpenApi allOpenApi() {
        return GroupedOpenApi.builder().group("all-API").pathsToMatch(allPaths).build();
    }

}