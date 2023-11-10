package test.controller.article;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class CommentDTO {

    private String memberId;

    @NotBlank
    private String content;
}
