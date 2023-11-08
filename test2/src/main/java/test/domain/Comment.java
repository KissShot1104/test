package test.domain;

import lombok.Data;
import test.domain.embedded.TimeStamps;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;    //게시글 아이디

    private String content;    //댓글 내용

    @Embedded
    private TimeStamps date;   //댓글 생성, 수정일자

}
