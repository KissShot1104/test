package test.controller.article;

import test.domain.embedded.TimeStamps;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ArticleDTO {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String category;

    @Embedded
    private TimeStamps timeStamps;

    private Long viewCount;

}
