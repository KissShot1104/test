package test.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;     //게시글 아이디
    private String imagePath;   //
}
