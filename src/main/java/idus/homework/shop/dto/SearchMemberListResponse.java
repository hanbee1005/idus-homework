package idus.homework.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(description = "여러 회원 목록 조회")
@Getter
@AllArgsConstructor
public class SearchMemberListResponse {

    @Schema(description = "페이징되어 조회된 회원 목록")
    List<SearchMemberWithLastOrder> members;
}
