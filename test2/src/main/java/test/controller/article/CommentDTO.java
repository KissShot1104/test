package test.controller.article;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class CommentDTO {
//이렇게 id를 직접 넣어줘도 되나?
    private Long id;

    private String memberId;

    @NotBlank
    private String content;
}
