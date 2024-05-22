package com.fastcampus.projectboard.domain.article.model;

import com.fastcampus.projectboard.common.entity.BaseEntity;
import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import com.fastcampus.projectboard.domain.hashtag.model.Hashtag;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @ToString.Exclude
    @JoinTable(
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "articleId"),
            inverseJoinColumns = @JoinColumn(name = "hashtagId")
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Hashtag> hashtags = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // article 테이블에서 온 데이터임을 명시
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Builder
    private Article(UserAccount userAccount, String title, String content) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
    }

    // Factory Method
    public static Article of(UserAccount userAccount, String title, String content) {
        return Article.builder()
                .userAccount(userAccount)
                .title(title)
                .content(content)
                .build();
    }

    public void addHashtag(Hashtag hashtag) {
        this.getHashtags().add(hashtag);
    }

    public void addHashtags(Collection<Hashtag> hashtags) {
        this.getHashtags().addAll(hashtags);
    }

    public void clearHashtags() {
        this.getHashtags().clear();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
