package test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import test.domain.embedded.TimeStamps;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@Builder

public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;      //멤버 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;    //게시글 아이디

    private String content;    //댓글 내용

    @Embedded
    private TimeStamps date;   //댓글 생성, 수정일자

    protected Comment(){}
}
