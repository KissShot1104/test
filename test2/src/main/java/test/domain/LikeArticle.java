package test.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class LikeArticle {
    @Id
    @GeneratedValue
    @Column(name = "like_article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
