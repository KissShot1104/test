package test.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import test.domain.embedded.TimeStamps;

import javax.persistence.*;


@Getter
@Entity
@Builder
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;    //아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  //작성자
    private String title;   //게시글 제목
    private String content;  //내용

    @Embedded
    private TimeStamps timeStamps;  //생성, 수정일자
    private Long viewCount;  //게시글 조회수
    private String category; //카테고리

    protected Article() {}



}
