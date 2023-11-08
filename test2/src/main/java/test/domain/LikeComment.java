package test.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LikeComment {
    @Id
    @GeneratedValue
    @Column(name = "like_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
