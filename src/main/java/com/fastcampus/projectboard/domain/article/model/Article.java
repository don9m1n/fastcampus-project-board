package com.fastcampus.projectboard.domain.article.model;

import com.fastcampus.projectboard.common.entity.BaseEntity;
import com.fastcampus.projectboard.domain.articlecomment.model.ArticleComment;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Getter
@ToString(exclude = "articleComments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Setter
    private String hashtag;

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // article 테이블에서 온 데이터임을 명시
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Builder
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // Factory Method
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return Article.builder()
                .userAccount(userAccount)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
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
