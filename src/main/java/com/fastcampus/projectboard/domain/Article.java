package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString(exclude = "articleComments") // 무한 참조 방지
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title; // 제목

    @Setter
    @Column(nullable = false, length = 10000)
    private String content; // 본문

    @Setter
    private String hashtag; // 해시태그

    // List, Set, Map 전부 가능
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // Static Factory Method
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        /**
         * 영속화되어있고, 식별자가 같은 경우에만 같은 객체로 인정
         * 모든 데이터가 같아도 영속화되지 않았다면 다른 객체로 보겠다.
         * 엔티티의 식별자가 같다면 같은 객체라는게 보장된다.
        */
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
