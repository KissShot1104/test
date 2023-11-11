package test.controller.article;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import test.domain.LikeArticle;
import test.domain.embedded.TimeStamps;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ArticleDTO {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String category;

    private LikeArticle likeArticle;

    @Embedded
    private TimeStamps timeStamps;

    private Long viewCount;

}
